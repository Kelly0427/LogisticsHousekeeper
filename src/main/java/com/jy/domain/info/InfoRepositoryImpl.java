package com.jy.domain.info;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


public class InfoRepositoryImpl implements InfoRepositoryCustom{
	@PersistenceContext
	private EntityManager em;
	//获取报修信息列表
	@Override
	public Page<Info> searchInfo(Pageable pageable, String operatorName,String phone) {
		List<Predicate> predicatesList = new ArrayList<Predicate>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = builder.createQuery(Object.class);
		Root<Info> root=query.from(Info.class);
		if (operatorName != null && operatorName.length() > 0) {
			predicatesList.add(builder.equal(root.get("fullName"), operatorName));
		}
		if (phone != null && phone.length() > 0) {
			predicatesList.add(builder.equal(root.get("phone"), phone));
		}
		query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		long total = (long) em.createQuery(query.select(builder.count(root))).getSingleResult();
		query.orderBy(builder.desc(root.get("id")));
		Query qPage = em.createQuery(query.select(root));
		Pageable localPageable = pageable;
		qPage.setMaxResults(localPageable.getPageSize());
		qPage.setFirstResult(localPageable.getPageNumber() * localPageable.getPageSize());
		return new PageImpl<Info>(qPage.getResultList(), localPageable, total);
	}
	//新提交的保修单
	@Override
	public Page<Info> loadInfoList(Pageable pageable,String status) {
		List<Predicate> predicatesList = new ArrayList<Predicate>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = builder.createQuery(Object.class);
		Root<Info> root=query.from(Info.class);
		if (status != null && status.length() > 0) {
			predicatesList.add(builder.equal(root.get("status"), status));
		}
		query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		long total = (long) em.createQuery(query.select(builder.count(root))).getSingleResult();
		query.orderBy(builder.desc(root.get("id")));
		Query qPage = em.createQuery(query.select(root));
		Pageable localPageable = pageable;
		qPage.setMaxResults(localPageable.getPageSize());
		qPage.setFirstResult(localPageable.getPageNumber() * localPageable.getPageSize());
		return new PageImpl<Info>(qPage.getResultList(), localPageable, total);
	}

}
