/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.LocationHistory;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.utils.Utils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
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
public class LocationHistoryRepositoryImpl implements BaseRepository<LocationHistory, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends LocationHistory> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<LocationHistory> criteria = builder.createQuery(LocationHistory.class);

        Root<LocationHistory> root = criteria.from(LocationHistory.class);

        int page = 1;

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

		criteria.orderBy(builder.desc(root.get("id")));
        Query<LocationHistory> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<LocationHistory> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                Join<LocationHistory, Device> locationHistoryDeviceJoin = root.join("device");
                predicates.add(
                        builder.or(
                                builder.like(builder.lower(root.<String>get("note")), "%" + q.toLowerCase() + "%"),
                                builder.equal(root.<String>get("device").get("id"), q) // NOTE: Be careful this may cause problems
                        )
                );
            }
            String location = queryParams.get("location");
            if (location != null && !location.isEmpty()) {
                predicates.add(builder.equal(root.get("location"), Integer.parseInt(location)));
            }
        }
        return predicates;
    }

    @Override
    public Optional<LocationHistory> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(LocationHistory.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        LocationHistory t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public LocationHistory save(LocationHistory locationHistory) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(locationHistory);
        return locationHistory;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<LocationHistory> root = criteriaQuery.from(LocationHistory.class);

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
