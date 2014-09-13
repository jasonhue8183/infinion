package com.my.tdex.dnb.sccb2.audit;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.my.tdex.dnb.sccb2.model.AuditTable;
import com.my.tdex.dnb.sccb2.service.AuditFlowService;
import com.my.tdex.dnb.sccb2.service.AuditTableService;
import com.my.tdex.dnb.sccb2.util.RequestHeaderInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.my.tdex.dnb.sccb2.audit.Audit;

@Aspect
@Component("auditControllerAspect")
@Configurable
public class AuditControllerAspect {
	
	@Autowired private AuditFlowService auditFlowService;
	
	@Autowired private HttpServletRequest request;
	
	@Autowired private RequestHeaderInfo requestHeaderInfoService;
	
	@Autowired private AuditTableService auditTableService;

	@Before("execution(public * com.my.tdex.dnb.sccb2.controller.*ManagedBean.*(..))  && @annotation(auditAnnotation) ")
	public void myManageBeanLog(JoinPoint joinPoint,Audit auditAnnotation) throws Exception{
		try {
			System.out.println("");
			System.out.println("");
			Map <String, String> headerInfo = requestHeaderInfoService.getHeadersInfo(request);
			ObjectMapper mapper = new ObjectMapper();
			
			AuditTable auditRecord = new AuditTable();
			auditRecord.setEntityname(( (Advised) joinPoint.getThis()).getTargetSource().getTargetClass().getSimpleName());
			auditRecord.setAction(auditAnnotation.value());
			auditRecord.setChangeset(mapper.writeValueAsString( headerInfo ) );
			auditTableService.createAudit(auditRecord);
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	auditFlowService.audit(auditAnnotation.value());
	}
	
	@AfterThrowing(pointcut = "execution( * *.*.*.*.*.*.*.*(..)))", throwing = "e")
	  public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {

	    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Okay - we're in the handler...");

	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    System.out.println("Write something in the log... We have caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + e.getMessage());
	  }

}
