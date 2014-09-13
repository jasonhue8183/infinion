package com.my.tdex.dnb.sccb2.model;
 
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Jason
 *
 */

@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private Date createddate = new Date();
    
	@Column(name="ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
 
    @Column(name="NAME", unique = true, nullable = false)
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
 
    @Column(name="SURNAME", unique = true, nullable = false)
    public String getSurname() {
        return surname;
    }
 
  
    public void setSurname(String surname) {
        this.surname = surname;
    }   
    
    
   
 
    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", name : ").append(getName());
        strBuff.append(", surname : ").append(getSurname());
        strBuff.append(", crateddate : ").append(getCreateddate());
        return strBuff.toString();
    }

    @Column(name="CREATEDDATE", nullable = false,columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
}