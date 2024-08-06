/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.DeviceCategory;
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
public class DeviceCategoryRepositoryImpl implements BaseRepository<DeviceCategory, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends DeviceCategory> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DeviceCategory> criteria = builder.createQuery(DeviceCategory.class);

        Root<DeviceCategory> root = criteria.from(DeviceCategory.class);

        int page = 1;

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

        Query<DeviceCategory> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<DeviceCategory> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                predicates.add(builder.like(builder.lower(root.<String>get("name")), "%" + q.toLowerCase() + "%"));
            }
            String type = queryParams.get("type");
            if (type != null && !type.isEmpty()) {
                predicates.add(builder.equal(root.get("deviceType"), Integer.parseInt(type)));
            }
        }
        return predicates;
    }

    @Override
    public Optional<DeviceCategory> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(DeviceCategory.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        DeviceCategory t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public DeviceCategory save(DeviceCategory deviceCategory) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(deviceCategory);
        return deviceCategory;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<DeviceCategory> root = criteriaQuery.from(DeviceCategory.class);

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
