package com.my.tdex.dnb.sccb2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERPRODUCTS")
public class CustomerProducts {
	
	private CustomerProductsCompositKey customerProductsCompositKey;
	private int orderlimit;
	
	@Id
	public CustomerProductsCompositKey getCustomerProductsCompositKey() {
		return customerProductsCompositKey;
	}
	public void setCustomerProductsCompositKey(
			CustomerProductsCompositKey customerProductsCompositKey) {
		this.customerProductsCompositKey = customerProductsCompositKey;
	}
	
	@Column(name="orderlimit")
	public int getOrderlimit() {
		return orderlimit;
	}
	public void setOrderlimit(int orderlimit) {
		this.orderlimit = orderlimit;
	}
}
