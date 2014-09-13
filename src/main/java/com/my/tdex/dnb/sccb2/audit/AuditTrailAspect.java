package com.my.tdex.dnb.sccb2.audit;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;











import com.my.tdex.dnb.sccb2.cache.LocalCacheManager;
import com.my.tdex.dnb.sccb2.model.AuditTable;
import com.my.tdex.dnb.sccb2.service.AuditFlowService;
import com.my.tdex.dnb.sccb2.service.AuditTableService;
import com.my.tdex.dnb.sccb2.util.JavaBeanCopier;
import com.my.tdex.dnb.sccb2.util.JsonUtils;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.infinispan.commons.api.BasicCache;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

@Aspect
@Component("auditTrailAspect")
@Configurable
public class AuditTrailAspect {
	
	@Autowired
	private AuditTableService auditTableService;
	
	@Resource
    private SessionFactory sessionFactory;
	
	@Autowired private AuditFlowService auditFlowService;
	
	
//	public ImmutableMap<String,Object> immutableMap = ImmutableMap.<String, Object>builder().build() ;
	private static BasicCache<Object, Object> caches = LocalCacheManager.getCache();
	
	
	@AfterReturning(pointcut ="execution(public * com.my.tdex.dnb.sccb2.dao..*.genericCreate(..))",returning= "bean")
	public void traceAfterCreate(JoinPoint joinPoint, Object bean) throws Exception {
		try {
			Advised advised = (Advised) joinPoint.getThis();
		    Class<?> cls = advised.getTargetSource().getTargetClass();
		    ObjectMapper mapper = new ObjectMapper();
		
		//  Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
		    System.out.println("");
		    System.out.println("");
			System.out.println("***Create*** AfterReturning is running!! intercepted method: " + joinPoint.getSignature().getName());
			System.out.println("***Create*** AfterReturning is running!! intercepted class : " + cls);
			System.out.println("Method returned value : " + bean);
			
			if (bean != null){
				AuditTable auditRecord = new AuditTable();
				auditRecord.setEntityname(bean.getClass().getSimpleName());
				auditRecord.setAction("Add");
				auditRecord.setChangeset(mapper.writeValueAsString( bean ) );
				auditTableService.createAudit(auditRecord);
			}
			
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
	}
	
	@AfterReturning(pointcut ="execution(public * com.my.tdex.dnb.sccb2.dao..*.genericDelete(..))",returning= "bean")
	public void traceAfterDelete(JoinPoint joinPoint, Object bean) throws Exception {
		try {
			Advised advised = (Advised) joinPoint.getThis();
		    Class<?> cls = advised.getTargetSource().getTargetClass();
		    ObjectMapper mapper = new ObjectMapper();
		    
			 //  Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
		    System.out.println("");
		    System.out.println("");
			System.out.println("***Delete*** AfterReturning is running!! intercepted method: " + joinPoint.getSignature().getName());
			System.out.println("***Delete*** AfterReturning is running!! intercepted class : " + cls);
			System.out.println("Method returned value is : " + bean);
			
			if (bean != null){
				AuditTable auditRecord = new AuditTable();
				auditRecord.setEntityname(bean.getClass().getSimpleName());
				auditRecord.setAction("Delete");
				auditRecord.setChangeset(mapper.writeValueAsString( bean ) );
				auditTableService.createAudit(auditRecord);
			}

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
	}
	
	@Before("execution(public * com.my.tdex.dnb.sccb2.dao..*.genericUpdate(..)) && args(bean) ")
	public void traceBeforeUpdate(JoinPoint joinPoint, Object bean) {
		try {
			Advised advised = (Advised) joinPoint.getThis();
		    Class<?> cls = advised.getTargetSource().getTargetClass();
		 
		    Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
		    if (auditAnnotation !=null ){
		    	 // get the primary id from object (bean)
			    ClassMetadata metadata = sessionFactory.getClassMetadata(bean.getClass());
				String entityPrimaryKey = metadata.getIdentifierPropertyName();
				
				// get the entity id from object (bean)
				JsonUtils utils = new JsonUtils();
				int typeid = Integer.parseInt(utils.getProperty(bean, entityPrimaryKey)) ;

				// find the genericGetById from dao implementation class
				Object service  = advised.getTargetSource().getTarget();
				Method findMethod = cls.getSuperclass().getMethod("genericGetById", new Class[] {int.class});
				
				// trigger the genericGetById with the identity (typeid)
		        Object oldValue = findMethod.invoke(service, typeid);
		        
		        String key = bean.toString() + typeid;
		        
		        Object keepInCacheObj  =  JavaBeanCopier.copy(oldValue); 
		        
		        caches.put(key, keepInCacheObj);
		    
		        System.out.println("");
			    System.out.println("");
	
			    System.out.println("*** entity primary key ***" + entityPrimaryKey);
				System.out.println("***update*** Before() is running!! intercepted method: " + joinPoint.getSignature().getName());
				System.out.println("***update*** Before() is running!! intercepted class: " + cls);		
		        System.out.println("***update*** Before() is running!! old Value : " + caches.get(key) );
		        System.out.println("***update*** Before() is running!! new Value : " + bean);
		        oldValue = null;
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
	}
	
	
	@AfterReturning("execution(public * com.my.tdex.dnb.sccb2.dao..*.genericUpdate(..)) && args(bean) ")
	public void traceAfterUpdate(JoinPoint joinPoint, Object bean) {
		String key = null;
		try {
			Advised advised = (Advised) joinPoint.getThis();
		    Class<?> cls = advised.getTargetSource().getTargetClass();
		    Auditable auditAnnotation = cls.getAnnotation(Auditable.class);
		    
		    if (auditAnnotation != null ){
		    	// get back the identity id after save
			    JsonUtils utils = new JsonUtils();
			    ClassMetadata metadata = sessionFactory.getClassMetadata(bean.getClass());
			    String entityPrimaryKey = metadata.getIdentifierPropertyName();
				int typeid = Integer.parseInt(utils.getProperty(bean,entityPrimaryKey)) ;
			    key = bean.toString() +  typeid;
			    Object oldValue =  caches.get(key);
			    
			    ObjectMapper mapper = new ObjectMapper();
			    
			    System.out.println("");
			    System.out.println("");
			    System.out.println("*** entity primary key ***" + entityPrimaryKey);
				System.out.println("***update*** After() is running!! intercepted method : " + joinPoint.getSignature().getName());
				System.out.println("***update*** After() is running!! intercepted class : " + cls);
			    System.out.println("***update*** After() is running!! old Value : " + oldValue);
				System.out.println("***update*** After() is running!! New Value : " + bean);
		  
				System.out.println("");
				System.out.println("");
			
				ChangeSetRecord changeSetRecord = new ChangeSetRecord ();
				changeSetRecord =  compareObjects(oldValue, bean);
				
				for (Entry<String, List<String>> entry :  changeSetRecord.entrySet()){
					System.out.println("@@@@@@@@@@@@  real change set, key = " + entry.getKey() + "  value ="  + entry.getValue());
				}
		
				if (changeSetRecord != null){
					AuditTable auditRecord = new AuditTable();
					auditRecord.setEntityname(bean.getClass().getSimpleName());
					auditRecord.setAction("Update");
					auditRecord.setChangeset(mapper.writeValueAsString( changeSetRecord ) );
					auditTableService.createAudit(auditRecord);
				}
				caches.remove(key);
		    }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class ChangeSetRecord extends HashMap<String, List<String>> {
		public void put(String key,String index) {
			List<String> current = get(key);
			if (current == null) {
				current = new ArrayList<String>();
				super.put(key, current);
			}
			current.add(index);
		}
	}
	
	public ChangeSetRecord compareObjects(Object oldObject, Object newObject) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		BeanMap map = new BeanMap(oldObject);
		PropertyUtilsBean propUtils = new PropertyUtilsBean();
		ChangeSetRecord changeSetRecord = new ChangeSetRecord();

		for (Object propNameObject : map.keySet()) {
			String propertyName = (String) propNameObject;
			Object property1 = propUtils.getProperty(oldObject, propertyName);
			Object property2 = propUtils.getProperty(newObject, propertyName);
			if (property1.equals(property2)) {
				System.out.println("  " + propertyName + " is equal");
			} else {
				System.out.println("> " + propertyName + " is different (oldValue=\"" + property1 + "\", newValue=\"" + property2 + "\")");


				changeSetRecord.put(propertyName, (property1 == null ? "" : property1.toString()));
				changeSetRecord.put(propertyName, (property2 == null ? "" : property2.toString()));
			}
		}
		return changeSetRecord;
	}
}
