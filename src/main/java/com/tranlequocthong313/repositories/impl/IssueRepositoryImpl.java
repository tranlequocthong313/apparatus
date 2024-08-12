/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.DeviceCategory;
import com.tranlequocthong313.models.Issue;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.repositories.IssueRepository;
import com.tranlequocthong313.utils.Utils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author tranlequocthong313
 */
@Repository
@Transactional
public class IssueRepositoryImpl implements IssueRepository {

	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	@Autowired
	private Utils utils;

	@Override
	public <S extends Issue> List<S> findAll(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Issue> criteria = builder.createQuery(Issue.class);

		Root<Issue> root = criteria.from(Issue.class);

		int page = 1;

		criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
		criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

		if (queryParams != null) {
			page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
		}

		criteria.orderBy(builder.desc(root.get("id")));
		Query<Issue> query = session.createQuery(criteria);
		utils.pagniate(query, page);
		return (List<S>) query.getResultList();
	}

	private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Issue> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				Join<Issue, Device> deviceJoin = root.join("device");
				Join<Device, DeviceCategory> deviceCategoryJoin = deviceJoin.join("deviceCategory");
				Join<Issue, User> userJoin = root.join("user");
				predicates.add(
					builder.or(
						builder.like(builder.lower(root.<String>get("title")), "%" + q.toLowerCase() + "%"),
						builder.like(builder.lower(root.<String>get("description")), "%" + q.toLowerCase() + "%"),
						builder.like(builder.lower(root.<String>get("note")), "%" + q.toLowerCase() + "%"),
						builder.like(builder.lower(deviceCategoryJoin.<String>get("name")), "%" + q.toLowerCase() + "%"),
						builder.like(builder.lower(userJoin.<String>get("fullName")), "%" + q.toLowerCase() + "%"),
						builder.equal(deviceJoin.get("id"), q.toLowerCase())
					)
				);
			}
			String device = queryParams.get("device");
			if (device != null && !device.isEmpty()) {
				predicates.add(builder.equal(root.get("device").get("id"), device));
			}
			String severity = queryParams.get("severity");
			if (severity != null && !severity.isEmpty()) {
				predicates.add(
					builder.like(root.get("severity"), severity)
				);
			}
			String done = queryParams.get("done");
			if (done != null && !done.isEmpty()) {
				predicates.add(
					builder.equal(root.get("done"), Boolean.parseBoolean(done))
				);
			}
		}
		return predicates;
	}

	@Override
	public Optional<Issue> findById(Integer id) {
		Session session = sessionFactory.getObject().getCurrentSession();
		return Optional.ofNullable(session.get(Issue.class, id));
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getObject().getCurrentSession();
		Issue t = getReferenceById(id);
		session.delete(t);
	}

	@Override
	public Issue save(Issue provider) {
		Session session = sessionFactory.getObject().getCurrentSession();
		session.saveOrUpdate(provider);
		return provider;
	}

	@Override
	public Long count(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);

		criteriaQuery
			.select(builder.count(root))
			.where(builder.and(
				getPredicates(
					queryParams,
					builder,
					root
				).toArray(new Predicate[0])));
		return session.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public Long totalCost(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<Issue> root = criteriaQuery.from(Issue.class);
		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String done = queryParams.getOrDefault("done", "true");
			predicates.add(builder.equal(root.get("done"), Boolean.valueOf(done)));
			String device = queryParams.get("device");
			if (device != null && !device.isEmpty()) {
				predicates.add(builder.equal(root.get("device").get("id"), device));
			}
		} else {
			predicates.add(builder.equal(root.get("done"), true));
		}
		criteriaQuery.select(builder.sum(root.get("cost"))).where(predicates.toArray(Predicate[]::new));
		return session.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public Long unresolvedDays(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Date> query = builder.createQuery(Date.class);

		Root<Issue> root = query.from(Issue.class);

		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String device = queryParams.get("device");
			if (device != null && !device.isEmpty()) {
				predicates.add(builder.equal(root.get("device").get("id"), device));
			}
		}
		predicates.add(builder.equal(root.get("done"), false));
		query.select(root.get("createdAt")).where(predicates.toArray(Predicate[]::new));
		query.orderBy(builder.asc(root.get("createdAt")));


		List<Date> results = session.createQuery(query).getResultList();
		Date earliestCreatedAt = results.isEmpty() ? null : results.get(0);
		if (earliestCreatedAt == null) {
			return 0L;
		}
		long diffInMillies = Math.abs(new Date().getTime() - earliestCreatedAt.getTime());
		return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	@Override
	public boolean exist(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);

		Root<Issue> root = criteria.from(Issue.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (queryParams != null) {
			String done = queryParams.get("done");
			if (done != null && !done.isEmpty()) {
				predicates.add(builder.equal(root.get("done"), Boolean.parseBoolean(done)));
			}
			String device = queryParams.get("device");
			if (device != null && !device.isEmpty()) {
				predicates.add(builder.equal(root.get("device").get("id"), device));
			}
		}

		criteria.select(builder.literal(1)).where(predicates.toArray(Predicate[]::new));
		return !session.createQuery(criteria).setMaxResults(1).getResultList().isEmpty();
	}
}
