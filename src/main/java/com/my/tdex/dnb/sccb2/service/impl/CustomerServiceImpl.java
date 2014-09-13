package com.my.tdex.dnb.sccb2.service.impl;
 
import com.my.tdex.dnb.sccb2.dao.CustomerDAO;
import com.my.tdex.dnb.sccb2.dao.GenericDao;
import com.my.tdex.dnb.sccb2.model.Customer;
import com.my.tdex.dnb.sccb2.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService{

	@Autowired
    public CustomerServiceImpl(CustomerDAO dao) {
		super();
		super.genericDao = (GenericDao<Customer>) dao;
		this.customerDao = dao;
	}

	@Autowired
    CustomerDAO customerDao;
 
    @Transactional(readOnly = false)
    public Customer getCustomerById(int id) throws Exception {
        return customerDao.getCustomerById(id);
    }
 
}