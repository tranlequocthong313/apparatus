/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.exceptions.DuplicateEntryException;
import com.tranlequocthong313.exceptions.UserNotFoundException;
import com.tranlequocthong313.models.DeviceCategory;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.tranlequocthong313.utils.Utils;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 * @author tranlequocthong313
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends User> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);

        Root<User> root = criteria.from(User.class);
        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        Query<User> query = session.createQuery(criteria);
        int page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }


    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<User> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                predicates.add(
                        builder.or(
                                builder.like(builder.lower(root.<String>get("username")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(root.<String>get("fullName")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(root.<String>get("email")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(root.<String>get("phoneNUmber")), "%" + q.toLowerCase() + "%")
                        )
                );
            }
        }
        return predicates;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<User> root = criteriaQuery.from(User.class);

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
    public Optional<User> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(User.class, id));
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query query = session.getNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        return (User) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public User save(User o) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(o);
        return o;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.delete(getReferenceById(id));
    }
}
