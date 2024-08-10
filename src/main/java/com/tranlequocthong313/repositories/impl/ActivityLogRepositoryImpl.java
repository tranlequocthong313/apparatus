/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.ActivityLog;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.utils.Utils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author tranlequocthong313
 */
@Repository
@Transactional
public class ActivityLogRepositoryImpl implements BaseRepository<ActivityLog, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends ActivityLog> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ActivityLog> criteria = builder.createQuery(ActivityLog.class);

        Root<ActivityLog> root = criteria.from(ActivityLog.class);

        int page = 1;

        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

        criteria.orderBy(builder.desc(root.get("id")));
        Query<ActivityLog> query = session.createQuery(criteria);
        utils.pagniate(query, page, "activity.log.pageSize");
        return (List<S>) query.getResultList();
    }

    @Override
    public Optional<ActivityLog> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(ActivityLog.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        ActivityLog t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public ActivityLog save(ActivityLog activityLog) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(activityLog);
        return activityLog;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<ActivityLog> root = criteriaQuery.from(ActivityLog.class);

        criteriaQuery
                .select(builder.count(root));
        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
