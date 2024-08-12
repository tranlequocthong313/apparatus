/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Repair;
import com.tranlequocthong313.models.RepairCategory;
import com.tranlequocthong313.repositories.RepairRepository;
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
public class RepairRepositoryImpl implements RepairRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Utils utils;

    @Override
    public <S extends Repair> List<S> findAll(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Repair> criteria = builder.createQuery(Repair.class);

        Root<Repair> root = criteria.from(Repair.class);

        int page = 1;

        criteria.where(getPredicates(queryParams, builder, root).toArray(Predicate[]::new));
        criteria.orderBy(utils.getOrdersBy(queryParams, builder, root));

        if (queryParams != null) {
            page = Integer.parseInt(queryParams.getOrDefault("page", "1"));
        }

		criteria.orderBy(builder.desc(root.get("id")));
        Query<Repair> query = session.createQuery(criteria);
        utils.pagniate(query, page);
        return (List<S>) query.getResultList();
    }

    private static List<Predicate> getPredicates(Map<String, String> queryParams, CriteriaBuilder builder, Root<Repair> root) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (queryParams != null) {
            String q = queryParams.get("q");
            if (q != null && !q.isEmpty()) {
                predicates.add(
                        builder.or(
                                builder.equal(root.<String>get("device").get("id"), q),
                                builder.like(builder.lower(root.<String>get("note")), "%" + q.toLowerCase() + "%"),
                                builder.like(builder.lower(root.<String>get("user").get("fullName")), "%" + q.toLowerCase() + "%")
                        )
                );
            }
            String repairedBy = queryParams.get("repairedby");
            if (repairedBy != null && !repairedBy.isEmpty()) {
                predicates.add(builder.equal(root.get("repairedBy"), Repair.RepairedBy.valueOf(repairedBy)));
            }
            String category = queryParams.get("category");
            if (category != null && !category.isEmpty()) {
                Join<Repair, RepairCategory> repairRepairCategoryJoin = root.join("repairCategorySet", JoinType.INNER);
                predicates.add(builder.equal(repairRepairCategoryJoin.get("id"), category));
            }
        }
        return predicates;
    }

    @Override
    public Optional<Repair> findById(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        return Optional.ofNullable(session.get(Repair.class, id));
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Repair t = getReferenceById(id);
        session.delete(t);
    }

    @Override
    public Repair save(Repair repair) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.saveOrUpdate(repair);
        return repair;
    }

    @Override
    public Long count(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Repair> root = criteriaQuery.from(Repair.class);

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
    public Long totalCost(Map<String, String> queryParams) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Repair> root = criteriaQuery.from(Repair.class);
        List<Predicate> predicates = new ArrayList<>();
        if (queryParams != null) {
            String device = queryParams.get("device");
            if (device != null && !device.isEmpty()) {
                predicates.add(builder.equal(root.get("device").get("id"), device));
            }
        }
        criteriaQuery.select(builder.sum(root.get("cost"))).where(predicates.toArray(Predicate[]::new));
        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
