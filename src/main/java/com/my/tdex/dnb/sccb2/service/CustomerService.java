package com.my.tdex.dnb.sccb2.service;
 
import com.my.tdex.dnb.sccb2.model.Customer;

public interface CustomerService extends GenericService<Customer>{
   
	/**
	 * Get customer
	 * @param id customerId
	 * @return Customer Object
	 * @throws Exception 
	 */
    public Customer getCustomerById(int id) throws Exception;
}