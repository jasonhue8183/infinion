package com.my.tdex.dnb.sccb2.dao;

import com.my.tdex.dnb.sccb2.model.AuditTable;

public interface AuditTableDAO extends GenericDao<AuditTable>{

	public AuditTable createAudit(AuditTable auditTable) throws Exception;
}
