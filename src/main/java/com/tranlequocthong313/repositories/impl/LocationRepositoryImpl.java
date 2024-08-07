/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Location;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.utils.Utils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

/**
 * @author tranlequocthong313
 */
@Repository
@Transactional
public class LocationRepositoryImpl implements BaseRepository<Location, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends Location> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Location> criteria = builder.createQuery(Location.class);

        Root<Location> root = criteria.from(Location.class);

        int page = 1;

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

        Query<Location> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Location> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                predicates.add(builder.like(builder.lower(root.<String>get("building")), "%" + q.toLowerCase() + "%"));
            }
        }
        return predicates;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(Location.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Location t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public Location save(Location location) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(location);
        return location;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Location> root = criteriaQuery.from(Location.class);

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
}
