/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.repositories.DeviceRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranlequocthong313
 */
@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Device> findAll() {
        Session session = factory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Device> criteriaQuery = builder.createQuery(Device.class);
        
        Root<Device> root = criteriaQuery.from(Device.class);
        
        criteriaQuery.select(root);
        
        Query query = session.createQuery(criteriaQuery);
        
        return (List<Device>) query.getResultList();

    }

}
