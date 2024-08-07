/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.DeviceCategory;
import com.tranlequocthong313.models.Reply;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 * @author tranlequocthong313
 */
@Repository
@Transactional
public class ReplyRepositoryImpl implements BaseRepository<Reply, Integer> {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends Reply> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Reply> criteriaQuery = builder.createQuery(Reply.class);

        Root<Reply> root = criteriaQuery.from(Reply.class);

        criteriaQuery.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));

        Query query = session.createQuery(criteriaQuery);
        int page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        utils.pagniate(query, page);

        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Reply> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String threadId = queryParams.get("threadid");
            if (threadId != null && !threadId.isEmpty()) {
                predicates.add(builder.equal(root.get("thread"), Integer.parseInt(threadId)));
            }
        }
        return predicates;
    }

    @Override
    public Optional<Reply> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(Reply.class, id));
    }

    @Override
    public Reply save(Reply o) {
        Session s = sessionFactory.getObject().getCurrentSession();
        s.saveOrUpdate(o);
        return o;
    }

    @Override
    public void delete(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        s.delete(getReferenceById(id));
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Reply> root = criteriaQuery.from(Reply.class);

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
