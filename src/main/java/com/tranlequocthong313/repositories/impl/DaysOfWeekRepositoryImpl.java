/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.DaysOfWeek;
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
public class DaysOfWeekRepositoryImpl implements BaseRepository<DaysOfWeek, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends DaysOfWeek> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DaysOfWeek> criteria = builder.createQuery(DaysOfWeek.class);

        Root<DaysOfWeek> root = criteria.from(DaysOfWeek.class);

        int page = 1;

        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

        Query<DaysOfWeek> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    @Override
    public Optional<DaysOfWeek> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(DaysOfWeek.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        DaysOfWeek t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public DaysOfWeek save(DaysOfWeek daysOfWeek) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(daysOfWeek);
        return daysOfWeek;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<DaysOfWeek> root = criteriaQuery.from(DaysOfWeek.class);
        criteriaQuery.select(builder.count(root));
        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
