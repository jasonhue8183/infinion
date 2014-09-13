package com.my.tdex.dnb.sccb2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUDITTABLE")
public class AuditTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private Date createddate = new Date();
    private String action;
    private String changeset;
    private String entityname;
    private String requestheader;
	private String errormsg;
    private String description;
    
	@Column(name="ID", unique = true, nullable = false)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="createddate",  nullable = false)
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	@Column(name="action",  nullable = false)
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Column(name="changeset",  nullable = false)
	public String getChangeset() {
		return changeset;
	}
	public void setChangeset(String changeset) {
		this.changeset = changeset;
	}
	
	@Column(name="entityname",  nullable = false)
	public String getEntityname() {
		return entityname;
	}
	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}
	
	@Column(name="requestheader",  nullable = false)
	public String getRequestheader() {
		return requestheader;
	}
	public void setRequestheader(String requestheader) {
		this.requestheader = requestheader;
	}
	
	@Column(name="errormsg",  nullable = false)
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	@Column(name="description",  nullable = false)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
