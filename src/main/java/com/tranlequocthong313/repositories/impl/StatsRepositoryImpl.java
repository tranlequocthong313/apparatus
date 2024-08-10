package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Issue;
import com.tranlequocthong313.models.Repair;
import com.tranlequocthong313.repositories.StatsRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    public List<Object[]> issuePerPeriod(int year, String period) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        Root<Issue> root = criteriaQuery.from(Issue.class);

        criteriaQuery.where(
                builder.equal(
                        builder.function(
                                "YEAR",
                                Integer.class,
                                root.get("createdAt")
                        ),
                        year
                )
        );

        criteriaQuery
                .multiselect(
                        builder.function(period, Integer.class, root.get("createdAt")),
                        builder.count(root.get("id"))
                )
                .groupBy(
                        builder.function(period, Integer.class, root.get("createdAt"))
                );

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> repairCostsPerPeriod(int year, String period) {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        Root<Repair> root = criteriaQuery.from(Repair.class);

        criteriaQuery.where(
                builder.equal(
                        builder.function(
                                "YEAR",
                                Integer.class,
                                root.get("completedDate")
                        ),
                        year
                )
        );

        criteriaQuery
                .multiselect(
                        builder.function(period, Integer.class, root.get("completedDate")),
                        builder.sum(root.get("cost"))
                )
                .groupBy(
                        builder.function(period, Integer.class, root.get("completedDate"))
                );

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> deviceStatuses() {
        Session session = factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

        Root<Device> root = criteriaQuery.from(Device.class);

        criteriaQuery
                .multiselect(
                        root.get("status"),
                        builder.count(root.get("id"))
                )
                .groupBy(
                        root.get("status")
                );

        return session.createQuery(criteriaQuery).getResultList();
    }
}
