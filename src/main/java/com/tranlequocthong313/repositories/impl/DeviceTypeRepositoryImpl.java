/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.DeviceType;
import com.tranlequocthong313.repositories.BaseRepository;
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
public class DeviceTypeRepositoryImpl implements BaseRepository<DeviceType, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public <S extends DeviceType> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DeviceType> criteria = builder.createQuery(DeviceType.class);
        criteria.select(criteria.from(DeviceType.class));
        Query<DeviceType> query = session.createQuery(criteria);
        return (List<S>) query.getResultList();
    }

    @Override
    public Optional<DeviceType> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(DeviceType.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        DeviceType t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<DeviceType> root = criteriaQuery.from(DeviceType.class);
        criteriaQuery
                .select(builder.count(root));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public DeviceType save(DeviceType deviceType) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(deviceType);
        return deviceType;
    }
}
