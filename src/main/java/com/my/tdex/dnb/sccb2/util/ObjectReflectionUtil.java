package com.my.tdex.dnb.sccb2.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ObjectReflectionUtil {

	public  void compareBeans(Object bean1, Object bean2, String... propertyNames)
	          throws IntrospectionException,
	          IllegalAccessException, InvocationTargetException {
	        Set<String> names = new HashSet<String>(Arrays
	            .asList(propertyNames));
	        BeanInfo beanInfo = Introspector.getBeanInfo(bean1
	            .getClass());
	        for (PropertyDescriptor prop : beanInfo.getPropertyDescriptors()) {
	          if (names.remove(prop.getName())) {
	            Method getter = prop.getReadMethod();
	            Object value1 = getter.invoke(bean1);
	            Object value2 = getter.invoke(bean2);
	            if (value1 == value2
	                || (value1 != null && value1.equals(value2))) {
	              continue;
	            }

	            System.out.println("Property = "+prop.getName() +" Value of been1 ="+value1 +" : Value of bean2 ="+value2);
	          }
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
            }
        }
        return changeSetRecord;
    }
	
/*	
	public Multimap<String,String> compareObjects(Object oldObject, Object newObject) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        BeanMap map = new BeanMap(oldObject);
        Multimap<String,String> objectMultimap = ArrayListMultimap.create();
        PropertyUtilsBean propUtils = new PropertyUtilsBean();

        for (Object propNameObject : map.keySet()) {
            String propertyName = (String) propNameObject;
            Object property1 = propUtils.getProperty(oldObject, propertyName);
            Object property2 = propUtils.getProperty(newObject, propertyName);
            if (property1.equals(property2)) {
                System.out.println("  " + propertyName + " is equal");
            } else {
                System.out.println("> " + propertyName + " is different (oldValue=\"" + property1 + "\", newValue=\"" + property2 + "\")");
                objectMultimap.put(propertyName, (property1 == null ? "" : property1.toString()) );
                objectMultimap.put(propertyName,(property2 == null ? "" : property2.toString()));
            }
        }
        return objectMultimap;
    }
*/	
	public Multimap<String,String> compareObjectsChangeSetRecord(Object oldObject, Object newObject) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        BeanMap map = new BeanMap(oldObject);
        Multimap<String,String> objectMultimap = ArrayListMultimap.create();
        PropertyUtilsBean propUtils = new PropertyUtilsBean();

        for (Object propNameObject : map.keySet()) {
            String propertyName = (String) propNameObject;
            Object property1 = propUtils.getProperty(oldObject, propertyName);
            Object property2 = propUtils.getProperty(newObject, propertyName);
            if (property1.equals(property2)) {
                System.out.println("  " + propertyName + " is equal");
            } else {
                System.out.println("> " + propertyName + " is different (oldValue=\"" + property1 + "\", newValue=\"" + property2 + "\")");
                objectMultimap.put(propertyName, (property1 == null ? "" : property1.toString()) );
                objectMultimap.put(propertyName,(property2 == null ? "" : property2.toString()));
            }
        }
        return objectMultimap;
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
    
    
}
