/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Provider;
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
public class ProviderRepositoryImpl implements BaseRepository<Provider, Integer> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends Provider> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Provider> criteria = builder.createQuery(Provider.class);

        Root<Provider> root = criteria.from(Provider.class);

        int page = 1;

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

        Query<Provider> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Provider> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                predicates.add(
                        builder.or(
                                builder.like(builder.lower(root.<String>get("name")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(root.<String>get("email")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(root.<String>get("phoneNumber")), "%" + q.toLowerCase() + "%")
                        )
                );
            }
        }
        return predicates;
    }

    @Override
    public Optional<Provider> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(Provider.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Provider t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public Provider save(Provider provider) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(provider);
        return provider;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Provider> root = criteriaQuery.from(Provider.class);

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
