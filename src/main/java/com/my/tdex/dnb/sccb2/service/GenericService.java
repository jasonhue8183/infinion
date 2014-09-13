package com.my.tdex.dnb.sccb2.service;

import java.util.List;

public interface GenericService <E>{
	E genericCreate(E persistentObject) throws Exception;

	E genericGetById(int id) throws Exception;

	List<E> getAll() throws Exception;

	void genericUpdate(E persistentObject) throws Exception;
	
	void createOrUpdate(E persistentObject) throws Exception;

	E genericDelete(E persistentObject) throws Exception;
}
