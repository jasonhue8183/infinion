package com.my.tdex.dnb.sccb2.dao;
 
import com.my.tdex.dnb.sccb2.model.Customer;

public interface CustomerDAO extends GenericDao<Customer> {
  
    public Customer getCustomerById(int id) throws Exception;
 
}