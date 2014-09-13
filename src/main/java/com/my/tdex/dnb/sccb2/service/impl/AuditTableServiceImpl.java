package com.my.tdex.dnb.sccb2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.tdex.dnb.sccb2.dao.AuditTableDAO;
import com.my.tdex.dnb.sccb2.model.AuditTable;
import com.my.tdex.dnb.sccb2.service.AuditTableService;

@Service("auditTableService")
public class AuditTableServiceImpl implements AuditTableService{

	@Autowired
	AuditTableDAO auditTableDAO;
	
	@Override
	public AuditTable createAudit(AuditTable auditTable) throws Exception {
		return auditTableDAO.createAudit(auditTable);
	}
}
