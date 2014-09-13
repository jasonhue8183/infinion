package com.my.tdex.dnb.sccb2.service.impl;


import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.tdex.dnb.sccb2.dao.GenericDao;
import com.my.tdex.dnb.sccb2.service.GenericService;

@Service("genericService")
public abstract class GenericServiceImpl <E > implements GenericService<E> {

	protected GenericDao <E> genericDao;
	
	@SuppressWarnings("unchecked")
    public GenericServiceImpl() {}
	
	@Transactional
	public E genericCreate(E persistentObject) throws Exception {
		return genericDao.genericCreate(persistentObject);
	}
	
	@Transactional( readOnly=true)
	public E genericGetById(int id) throws Exception {
		return genericDao.genericGetById(id);
	}
	
	@Transactional(readOnly=true)
	public List<E> getAll() throws Exception {
		return genericDao.getAll();
	}
	
	@Transactional
	public void createOrUpdate(E persistentObject) throws Exception {
		genericDao.createOrUpdate(persistentObject);
	}
	
	@Transactional
	public void genericUpdate(E persistentObject) throws Exception {
		genericDao.genericUpdate(persistentObject);
	}

	@Transactional
	public E genericDelete(E persistentObject) throws Exception {
		return genericDao.genericDelete(persistentObject);
	}
}
