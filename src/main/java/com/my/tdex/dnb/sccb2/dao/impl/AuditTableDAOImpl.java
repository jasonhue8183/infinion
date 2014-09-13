package com.my.tdex.dnb.sccb2.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.tdex.dnb.sccb2.dao.AuditTableDAO;
import com.my.tdex.dnb.sccb2.model.AuditTable;

@Repository("auditTableDAO")
public class AuditTableDAOImpl extends GenericDaoImpl<AuditTable> implements AuditTableDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	public AuditTableDAOImpl() {
		super(AuditTable.class);
	}

	@Override
	public AuditTable createAudit(AuditTable auditTable) throws Exception {
		return super.notAuditableGenericCreate(auditTable);
	}

}
