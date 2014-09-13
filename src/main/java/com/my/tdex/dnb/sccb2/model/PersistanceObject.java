package com.my.tdex.dnb.sccb2.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistanceObject implements Serializable {
	
	@javax.persistence.Id
	protected  String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
