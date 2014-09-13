package com.my.tdex.dnb.sccb2.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerProductsCompositKey implements Serializable{
	
	private int customerid;
	private int productid;

	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
}
