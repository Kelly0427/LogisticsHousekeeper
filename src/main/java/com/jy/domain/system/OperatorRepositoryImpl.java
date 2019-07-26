package com.jy.domain.system;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OperatorRepositoryImpl implements OperatorRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

}
