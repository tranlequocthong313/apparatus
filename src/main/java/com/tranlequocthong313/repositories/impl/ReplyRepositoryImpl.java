/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories.impl;

import com.tranlequocthong313.models.Reply;
import com.tranlequocthong313.repositories.BaseRepository;
import com.tranlequocthong313.utils.Utils;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranlequocthong313
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ReplyRepositoryImpl implements BaseRepository<Reply, Integer> {

	@Autowired
	private Environment env;
	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Override
	public <S extends Reply> List<S> findAll(Map<String, String> queryParams) {
		Session session = sessionFactory.getObject().getCurrentSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Reply> criteriaQuery = builder.createQuery(Reply.class);

		Root<Reply> root = criteriaQuery.from(Reply.class);

		if (queryParams != null) {
			String threadId = queryParams.get("threadid");
			if (threadId != null && !threadId.isEmpty()) {
				criteriaQuery.where(builder.equal(root.get("thread"), Integer.parseInt(threadId)));
			}

		}

		criteriaQuery.select(root);

		Query query = session.createQuery(criteriaQuery);
		String page = queryParams.get("page");
		Utils.pagniate(query, page);

		return (List<S>) query.getResultList();
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

}
