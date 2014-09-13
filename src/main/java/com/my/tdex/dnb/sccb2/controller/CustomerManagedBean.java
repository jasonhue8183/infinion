package com.my.tdex.dnb.sccb2.controller;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.my.tdex.dnb.sccb2.audit.Audit;
import com.my.tdex.dnb.sccb2.model.Customer;
import com.my.tdex.dnb.sccb2.pojo.CustomerPojo;
import com.my.tdex.dnb.sccb2.service.CustomerService;

@Component
@Scope("session")
public class CustomerManagedBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
    private CustomerService customerService;
    
	@Autowired
    private CustomerPojo customerPojo ;
    
	@PostConstruct
    public void init() throws Exception {
		customerPojo = null;
		customerPojo = new CustomerPojo();
		customerPojo.showAddEdit = false;
    	getCustomerList();              
    } 
	
	
	@Audit("Add Customer")
	public void addCustomer() throws Exception {
    	Customer newCustomer = new Customer ();
    	newCustomer.setName(customerPojo.customer.getName());
    	newCustomer.setSurname(customerPojo.customer.getSurname());
        customerService.genericCreate(newCustomer);
        init();
    }
	
	
    public void initEdit(int id) throws Exception{
    	customerPojo.customer = new Customer();
    	customerPojo.customer = customerService.genericGetById(id);
    	customerPojo.showAddEdit = true;
    }
    
    public void initAdd(){
    	customerPojo.customer = new Customer();
    	customerPojo.showAddEdit = true;
    }
  
    public List<Customer> getCustomerList() throws Exception {
    	customerPojo.customerList = new ArrayList<Customer> ();
    	customerPojo.customerList.addAll(customerService.getAll());
        return customerPojo.customerList;
    }
    
    @Audit("View Customer Page")
    public String viewCustomerPage(){
        return "viewCustomerPage";
    }
    
    public String backToHome(){
        return "home";
    }
 
    public void deleteCustomer() throws Exception{
    	for(Customer customer: customerPojo.selectedCustomer){
    		customerService.genericDelete(customer);
    	}
    	init();
    }
    
    public void updateCustomer() throws Exception{
    	customerService.genericUpdate(customerPojo.customer);
    	init();
    }
  
  
    public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CustomerPojo getCustomerPojo() {
		return customerPojo;
	}

	public void setCustomerPojo(CustomerPojo customerPojo) {
		this.customerPojo = customerPojo;
	}
 
}