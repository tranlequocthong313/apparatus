package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Device;
import com.tranlequocthong313.models.Issue;
import com.tranlequocthong313.models.Repair;
import com.tranlequocthong313.repositories.StatsRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Object[]> repairCountByRepairedBy(Map<String, String> queryParams) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Repair> root = criteriaQuery.from(Repair.class);

		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				predicates.add(
					builder.equal(
						root.get("device").get("id"), q
					)
				);
			} else {
				return null;
			}
			String month = queryParams.getOrDefault("month", String.valueOf(LocalDate.now().getMonth().getValue()));
			String year = queryParams.getOrDefault("year", String.valueOf(Year.now().getValue()));
			predicates.add(
				builder.and(
					builder.equal(
						builder.function("MONTH", Integer.class, root.get("completedDate")), month
					),
					builder.equal(
						builder.function("YEAR", Integer.class, root.get("completedDate")), year
					)
				)
			);
		}

		criteriaQuery.where(predicates.toArray(Predicate[]::new));

		criteriaQuery.multiselect(
			root.get("repairedBy"),
			builder.count(root.get("id"))
		);

		criteriaQuery.groupBy(root.get("repairedBy"));

		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Object[]> repairCostsPerMonth(Map<String, String> queryParams) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Repair> root = criteriaQuery.from(Repair.class);

		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				predicates.add(
					builder.equal(
						root.get("device").get("id"), q
					)
				);
			} else {
				return null;
			}
			String month = queryParams.getOrDefault("month", String.valueOf(LocalDate.now().getMonth().getValue()));
			String year = queryParams.getOrDefault("year", String.valueOf(Year.now().getValue()));
			predicates.add(
				builder.and(
					builder.equal(
						builder.function("MONTH", Integer.class, root.get("completedDate")), month
					),
					builder.equal(
						builder.function("YEAR", Integer.class, root.get("completedDate")), year
					)
				)
			);
		}

		criteriaQuery.where(predicates.toArray(Predicate[]::new));

		criteriaQuery.multiselect(
			builder.function("DAY", Integer.class, root.get("completedDate")),
			builder.sum(root.get("cost"))
		);

		criteriaQuery.groupBy(
			builder.function("DAY", Integer.class, root.get("completedDate"))
		);
		criteriaQuery.orderBy(
			builder.asc(builder.function("DAY", Integer.class, root.get("completedDate")))
		);

		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Object[]> deviceCategoryRepairCountByRepairedBy(Map<String, String> queryParams) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Repair> root = criteriaQuery.from(Repair.class);

		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("id"), Integer.parseInt(q)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String category = queryParams.get("category");
			if (category != null && !category.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("id"), Integer.parseInt(category)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String month = queryParams.getOrDefault("month", String.valueOf(LocalDate.now().getMonth().getValue()));
			String year = queryParams.getOrDefault("year", String.valueOf(Year.now().getValue()));
			predicates.add(
				builder.and(
					builder.equal(
						builder.function("MONTH", Integer.class, root.get("completedDate")), month
					),
					builder.equal(
						builder.function("YEAR", Integer.class, root.get("completedDate")), year
					)
				)
			);
		}

		criteriaQuery.where(predicates.toArray(Predicate[]::new));

		criteriaQuery.multiselect(
			root.get("repairedBy"),
			builder.count(root.get("id"))
		);

		criteriaQuery.groupBy(root.get("repairedBy"));

		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Object[]> deviceCategoryRepairCostsPerMonth(Map<String, String> queryParams) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Repair> root = criteriaQuery.from(Repair.class);

		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("id"), Integer.parseInt(q)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String category = queryParams.get("category");
			if (category != null && !category.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("id"), Integer.parseInt(category)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String month = queryParams.getOrDefault("month", String.valueOf(LocalDate.now().getMonth().getValue()));
			String year = queryParams.getOrDefault("year", String.valueOf(Year.now().getValue()));
			predicates.add(
				builder.and(
					builder.equal(
						builder.function("MONTH", Integer.class, root.get("completedDate")), month
					),
					builder.equal(
						builder.function("YEAR", Integer.class, root.get("completedDate")), year
					)
				)
			);
		}

		criteriaQuery.where(predicates.toArray(Predicate[]::new));

		criteriaQuery.multiselect(
			builder.function("DAY", Integer.class, root.get("completedDate")),
			builder.sum(root.get("cost"))
		);

		criteriaQuery.groupBy(
			builder.function("DAY", Integer.class, root.get("completedDate"))
		);
		criteriaQuery.orderBy(
			builder.asc(builder.function("DAY", Integer.class, root.get("completedDate")))
		);

		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Object[]> deviceTypeRepairCountByRepairedBy(Map<String, String> queryParams) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Repair> root = criteriaQuery.from(Repair.class);

		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("deviceType").get("id"), Integer.parseInt(q)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String type = queryParams.get("type");
			if (type != null && !type.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("deviceType").get("id"), Integer.parseInt(type)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String month = queryParams.getOrDefault("month", String.valueOf(LocalDate.now().getMonth().getValue()));
			String year = queryParams.getOrDefault("year", String.valueOf(Year.now().getValue()));
			predicates.add(
				builder.and(
					builder.equal(
						builder.function("MONTH", Integer.class, root.get("completedDate")), month
					),
					builder.equal(
						builder.function("YEAR", Integer.class, root.get("completedDate")), year
					)
				)
			);
		}

		criteriaQuery.where(predicates.toArray(Predicate[]::new));

		criteriaQuery.multiselect(
			root.get("repairedBy"),
			builder.count(root.get("id"))
		);

		criteriaQuery.groupBy(root.get("repairedBy"));

		return session.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<Object[]> deviceTypeRepairCostsPerMonth(Map<String, String> queryParams) {
		Session session = factory.getObject().getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Repair> root = criteriaQuery.from(Repair.class);

		List<Predicate> predicates = new ArrayList<>();
		if (queryParams != null) {
			String q = queryParams.get("q");
			if (q != null && !q.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("deviceType").get("id"), Integer.parseInt(q)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String type = queryParams.get("type");
			if (type != null && !type.isEmpty()) {
				try {
					predicates.add(
						builder.equal(
							root.get("device").get("deviceCategory").get("deviceType").get("id"), Integer.parseInt(type)
						)
					);
				} catch (NumberFormatException e) {
				}
			}
			String month = queryParams.getOrDefault("month", String.valueOf(LocalDate.now().getMonth().getValue()));
			String year = queryParams.getOrDefault("year", String.valueOf(Year.now().getValue()));
			predicates.add(
				builder.and(
					builder.equal(
						builder.function("MONTH", Integer.class, root.get("completedDate")), month
					),
					builder.equal(
						builder.function("YEAR", Integer.class, root.get("completedDate")), year
					)
				)
			);
		}

		criteriaQuery.where(predicates.toArray(Predicate[]::new));

		criteriaQuery.multiselect(
			builder.function("DAY", Integer.class, root.get("completedDate")),
			builder.sum(root.get("cost"))
		);

		criteriaQuery.groupBy(
			builder.function("DAY", Integer.class, root.get("completedDate"))
		);
		criteriaQuery.orderBy(
			builder.asc(builder.function("DAY", Integer.class, root.get("completedDate")))
		);

		return session.createQuery(criteriaQuery).getResultList();
	}
}
