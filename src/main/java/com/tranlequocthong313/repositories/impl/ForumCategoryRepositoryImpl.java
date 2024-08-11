/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.exceptions.DuplicateEntryException;
import com.tranlequocthong313.models.ForumCategory;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.utils.Utils;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author tranlequocthong313
 */
@Repository
@Transactional
public class ForumCategoryRepositoryImpl implements BaseRepository<ForumCategory, Integer> {

	@Autowired
	private LocalSessionFactoryBean sessionFactory;
	@Autowired
	private Utils utils;

	@Override
	public Optional<ForumCategory> findById(Integer id) {
		Session session = sessionFactory.getObject().getCurrentSession();
		return Optional.ofNullable(session.get(ForumCategory.class, id));
	}

	@Override
	public ForumCategory save(ForumCategory o) {
		Session session = sessionFactory.getObject().getCurrentSession();
		try {
			session.saveOrUpdate(o);
		} catch (ConstraintViolationException e) {
			throw new DuplicateEntryException("Duplicate entry for forum category");
		}
		return o;
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getObject().getCurrentSession();
		ForumCategory c = getReferenceById(id);
		session.delete(c);
	}

	@Override
	public Long count(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<ForumCategory> root = criteriaQuery.from(ForumCategory.class);
		criteriaQuery
			.select(builder.count(root));
		return session.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public <S extends ForumCategory> List<S> findAll(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ForumCategory> criteriaQuery = builder.createQuery(ForumCategory.class);

		Root<ForumCategory> root = criteriaQuery.from(ForumCategory.class);

		int page = 1;
		criteriaQuery.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
		criteriaQuery.select(root);

		if (queryParams != null) {
			page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
		}
		Query query = session.createQuery(criteriaQuery);
		utils.pagniate(query, page);
		return (List<S>) query.getResultList();
	}

	private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<ForumCategory> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				predicates.add(
					builder.like(builder.lower(root.<String>get("name")), "%" + q.toLowerCase() + "%")
				);
			}
		}
		return predicates;
	}
}
