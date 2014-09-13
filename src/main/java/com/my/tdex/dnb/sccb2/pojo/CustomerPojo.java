package com.my.tdex.dnb.sccb2.pojo;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Component;

import com.my.tdex.dnb.sccb2.model.Customer;

@Component
public class CustomerPojo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public List<Customer> customerList;
	public List<Customer> selectedCustomer ;
	public Customer customer;
	public boolean showAddEdit;

	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public List<Customer> getSelectedCustomer() {
		return selectedCustomer;
	}
	public void setSelectedCustomer(List<Customer> selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public boolean isShowAddEdit() {
		return showAddEdit;
	}
	public void setShowAddEdit(boolean showAddEdit) {
		this.showAddEdit = showAddEdit;
	}
}
