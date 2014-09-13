package com.my.tdex.dnb.sccb2.dao.impl;
 
import com.my.tdex.dnb.sccb2.audit.Auditable;
import com.my.tdex.dnb.sccb2.dao.CustomerDAO;
import com.my.tdex.dnb.sccb2.model.Customer;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
@Auditable(module = "customerDAOImpl", type = "customerDAOImpl")
public class CustomerDAOImpl  extends GenericDaoImpl<Customer> implements CustomerDAO{
	
	@Autowired
	SessionFactory sessionFactory;

    public CustomerDAOImpl() {
		super(Customer.class);
	}
    
    public Customer getCustomerById(int id) throws Exception {
    	return super.genericGetById(id);
    }
 
}