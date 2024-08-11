/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.*;
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
public class MaintenanceRepositoryImpl implements BaseRepository<Maintenance, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends Maintenance> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Maintenance> criteria = builder.createQuery(Maintenance.class);

        Root<Maintenance> root = criteria.from(Maintenance.class);

        int page = 1;

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

        Query<Maintenance> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Maintenance> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                Join<Maintenance, Device> maintenanceDeviceJoin = root.join("device");
                Join<Device, DeviceCategory> deviceCategoryJoin = maintenanceDeviceJoin.join("deviceCategory");
                predicates.add(
                        builder.or(
                                builder.like(builder.lower(root.<String>get("summary")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(root.<String>get("description")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(deviceCategoryJoin.<String>get("name")), "%" + q.toLowerCase() + "%")
                        )
                );
            }
            String type = queryParams.get("type");
            if (type != null && !type.isEmpty()) {
                predicates.add(builder.equal(root.get("type"), Maintenance.Type.valueOf(type)));
            }
            String recurrenceType = queryParams.get("recurrence");
            if (recurrenceType != null && !recurrenceType.isEmpty()) {
                predicates.add(builder.equal(root.get("recurrenceType"), Maintenance.RecurrenceType.valueOf(recurrenceType)));
            }
            String endRecurrenceType = queryParams.get("endrecurrence");
            if (endRecurrenceType != null && !endRecurrenceType.isEmpty()) {
                predicates.add(builder.equal(root.get("endRecurrenceType"), Maintenance.EndRecurrenceType.valueOf(endRecurrenceType)));
            }
            String deviceType = queryParams.get("devicetype");
            if (deviceType != null && !deviceType.isEmpty()) {
                Join<Maintenance, Device> maintenanceDeviceJoin = root.join("device");
                Join<Device, DeviceCategory> deviceCategoryJoin = maintenanceDeviceJoin.join("deviceCategory");
                Join<DeviceCategory, DeviceType> deviceTypeJoin = deviceCategoryJoin.join("deviceType");
                predicates.add(builder.equal(deviceTypeJoin.get("id"), Integer.parseInt(deviceType)));
            }
        }
        return predicates;
    }

    @Override
    public Optional<Maintenance> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(Maintenance.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Maintenance t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public Maintenance save(Maintenance maintenance) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(maintenance);
        return maintenance;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Maintenance> root = criteriaQuery.from(Maintenance.class);

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
