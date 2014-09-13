package com.my.tdex.dnb.sccb2.dao;

import java.io.Serializable;
import java.util.List;

import com.my.tdex.dnb.sccb2.model.PersistanceObject;

public interface GenericDao<E> {
	E genericCreate(E persistentObject) throws Exception;
	
	E notAuditableGenericCreate(E persistentObject) throws Exception;

	E genericGetById(int id) throws Exception;

	List<E> getAll() throws Exception;

	E genericUpdate(E persistentObject) throws Exception;
	
	void createOrUpdate(E persistentObject) throws Exception;

	E genericDelete(E persistentObject) throws Exception;
}
