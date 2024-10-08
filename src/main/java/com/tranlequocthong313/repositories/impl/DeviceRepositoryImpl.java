/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.DeviceCategory;
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
public class DeviceRepositoryImpl implements BaseRepository<Device, String> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends Device> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Device> criteria = builder.createQuery(Device.class);

        Root<Device> root = criteria.from(Device.class);

        int page = 1;

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

        Query<Device> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Device> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                Join<Device, DeviceCategory> deviceCategoryJoin = root.join("deviceCategory");
                predicates.add(builder.or(
                        builder.like(builder.lower(deviceCategoryJoin.<String>get("name")), "%" + q.toLowerCase() + "%"),
                        builder.like(builder.lower(root.<String>get("id")), "%" + q.toLowerCase() + "%")
                ));
            }
            String type = queryParams.get("type");
            if (type != null && !type.isEmpty()) {
                Join<Device, DeviceCategory> deviceCategoryJoin = root.join("deviceCategory");
                predicates.add(builder.equal(deviceCategoryJoin.get("deviceType"), Integer.parseInt(type)));
            }
            String location = queryParams.get("location");
            if (location != null && !location.isEmpty()) {
                predicates.add(builder.equal(root.get("location"), Integer.parseInt(location)));
            }
            String provider = queryParams.get("provider");
            if (provider != null && !provider.isEmpty()) {
                predicates.add(builder.equal(root.get("provider"), Integer.parseInt(provider)));
            }
            String status = queryParams.get("status");
            if (status != null && !status.isEmpty()) {
                predicates.add(builder.equal(root.get("status"), Device.Status.valueOf(status)));
            }
        }
        return predicates;
    }

    @Override
    public Optional<Device> findById(String id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(Device.class, id));
    }

    @Override
    public void delete(String id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Device t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public Device save(Device device) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(device);
        return device;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Device> root = criteriaQuery.from(Device.class);

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

