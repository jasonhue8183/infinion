package com.my.tdex.dnb.sccb2.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.my.tdex.dnb.sccb2.dao.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDaoImpl<E>  implements GenericDao<E> {

	private Class<E> type;

	@Resource
    private SessionFactory sessionFactory;

	public GenericDaoImpl(Class<E> persistentClass) {
		type = persistentClass;
	}

	@SuppressWarnings("unchecked")
	public E genericCreate(E persistentObject) throws Exception {
		try{
			Serializable generatedId = sessionFactory.getCurrentSession().save(persistentObject);
			return genericGetById((Integer) generatedId);
		}catch(Exception e){
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public E genericGetById(int id) throws Exception {
        try{
        	E value = (E) sessionFactory.getCurrentSession().get(type, id);
    		if (value == null) {
                return null;
            }
            return value;
		}catch(Exception e){
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<E> getAll() throws Exception {
		try{
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(type);
			return crit.list();
		}catch(Exception e){
			throw e;
		}
	}
	
	public void createOrUpdate(E persistentObject) throws Exception {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(persistentObject);
		}catch(Exception e){
			throw e;
		}
	}


	public E genericUpdate(E persistentObject) throws Exception {
		try{
			sessionFactory.getCurrentSession().merge(persistentObject);
			return persistentObject;
		}catch(Exception e){
			throw e;
		}
	}

	public E genericDelete(E persistentObject) throws Exception {
		try{
			sessionFactory.getCurrentSession().delete(persistentObject);
			return persistentObject;
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public E notAuditableGenericCreate(E persistentObject) throws Exception {
		try{
			Serializable generatedId = sessionFactory.getCurrentSession().save(persistentObject);
			return genericGetById((Integer) generatedId);
		}catch(Exception e){
			throw e;
		}
	}

}