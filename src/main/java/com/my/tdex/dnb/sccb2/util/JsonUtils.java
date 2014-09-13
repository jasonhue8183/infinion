package com.my.tdex.dnb.sccb2.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtils {
	public String getProperty(Object bean, String property) {
		
		String propertyValue="";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			String jsonString = "";
		
			jsonString = mapper.writeValueAsString( bean );
			JsonNode rootNode = mapper.readTree(jsonString);
			JsonNode idNode = rootNode.path(property);
			propertyValue = idNode.asText() ;
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
		
		return propertyValue;
	}
	
	public Map<String, String> convertJsonToMap(String jsonString){
		
		Map<String,String> map = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			//convert JSON string to Map
			map = mapper.readValue(jsonString, new TypeReference<HashMap<String,String>>(){});
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

}
