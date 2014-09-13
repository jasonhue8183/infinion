package com.my.tdex.dnb.sccb2.controller;
 
import java.io.Serializable;
import java.sql.SQLException;

import com.my.tdex.dnb.sccb2.audit.Audit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.sql.SQLException;

@Component
@Scope("session")
public class ProductsManagedBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Audit("View Products Page")
    public String viewProductPage(){
        return "viewProductPage";
    }
	
	public void hitException() throws Exception{
		throw new Exception();
	}
	
	public void hitSqlException() throws SQLException{
		throw new SQLException();
	}
    
    public String backToHome(){
    	return "home";
    }
 
}