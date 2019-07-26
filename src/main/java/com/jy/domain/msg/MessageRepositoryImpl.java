package com.jy.domain.msg;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MessageRepositoryImpl implements MessageRepositoryCustom{
	@PersistenceContext
	private EntityManager em;
	//显示最新发布内容
	@Override
	public List<Message> searchMsg() {
		List<Predicate> predicatesList = new ArrayList<Predicate>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = builder.createQuery(Object.class);
		Root<Message> root = query.from(Message.class);
		query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		query.orderBy(builder.desc(root.get("recordTime")));
		Query qPage = em.createQuery(query.select(root));
		return qPage.getResultList();
	}

}
