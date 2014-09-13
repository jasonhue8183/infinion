package com.my.tdex.dnb.sccb2.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.my.tdex.dnb.sccb2.service.AuditFlowService;
import com.my.tdex.dnb.sccb2.util.RequestHeaderInfo;

@Service("auditFlowService")
public class AuditFlowServiceImpl implements AuditFlowService{
	
	private @Autowired HttpServletRequest request;
	
	private @Autowired RequestHeaderInfo requestHeaderInfoService;

	@Override
	public void audit(String actionName) {
		
		System.out.println("");
		System.out.println("@@ The user action :" + actionName);
		Map <String, String> headerInfo = requestHeaderInfoService.getHeadersInfo(request);
		for(Map.Entry<String, String> entry : headerInfo.entrySet()){
			System.out.println("Header Name :" + entry.getKey() + ", Header Value :" + entry.getValue());
		}
	}
}
