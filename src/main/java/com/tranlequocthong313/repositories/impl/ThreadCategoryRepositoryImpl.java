/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.exceptions.DuplicateEntryException;
import com.tranlequocthong313.exceptions.ThreadCategoryNotFoundException;
import com.tranlequocthong313.models.ThreadCategory;
import com.tranlequocthong313.repositories.BaseRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranlequocthong313
 */
@Repository
@Transactional
public class ThreadCategoryRepositoryImpl implements BaseRepository<ThreadCategory, Integer> {

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Override
	public Optional<ThreadCategory> findById(Integer id) {
		Session session = sessionFactory.getObject().getCurrentSession();
		return Optional.ofNullable(session.get(ThreadCategory.class, id));
	}

	@Override
	public ThreadCategory save(ThreadCategory o) {
		Session session = sessionFactory.getObject().getCurrentSession();
		try {
			session.saveOrUpdate(o);
		} catch (ConstraintViolationException e) {
			throw new DuplicateEntryException("Duplicate entry for thread category");
		}
		return o;
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getObject().getCurrentSession();
		ThreadCategory c = getReferenceById(id);
		session.delete(c);
	}

	@Override
	public <S extends ThreadCategory> List<S> findAll(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ThreadCategory> criteriaQuery = builder.createQuery(ThreadCategory.class);

		Root<ThreadCategory> root = criteriaQuery.from(ThreadCategory.class);

		criteriaQuery.select(root);

		Query query = session.createQuery(criteriaQuery);

		return (List<S>) query.getResultList();
	}

}
