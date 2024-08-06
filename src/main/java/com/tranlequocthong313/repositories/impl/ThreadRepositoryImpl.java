/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.DeviceCategory;
import com.tranlequocthong313.models.Thread;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.utils.Utils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

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
public class ThreadRepositoryImpl implements BaseRepository<Thread, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends Thread> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Thread> criteria = builder.createQuery(Thread.class);

        Root<Thread> root = criteria.from(Thread.class);

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        Query<Thread> query = session.createQuery(criteria);
        int page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Thread> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                predicates.add(builder.like(builder.lower(root.<String>get("title")), "%" + q.toLowerCase() + "%"));
            }

            String categoryId = queryParams.get("category");
            if (categoryId != null && !categoryId.isEmpty()) {
                predicates.add(builder.equal(root.get("threadCategory"), Integer.parseInt(categoryId)));
            }
        }
        return predicates;
    }

    @Override
    public Optional<Thread> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(Thread.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Thread t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Thread> root = criteriaQuery.from(Thread.class);

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
    public Thread save(Thread thread) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(thread);
        return thread;
    }
}
