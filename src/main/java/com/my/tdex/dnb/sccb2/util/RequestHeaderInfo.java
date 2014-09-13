package com.my.tdex.dnb.sccb2.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service("requestHeaderInfoService")
public class RequestHeaderInfo {
	
	//get user agent
		public String getUserAgent(HttpServletRequest  request) {
			return request.getHeader("user-agent");
		}
	 
		//get request headers
		public Map<String, String> getHeadersInfo(HttpServletRequest  request) {
	 
			Map<String, String> map = new HashMap<String, String>();
	 
			Enumeration headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String key = (String) headerNames.nextElement();
				String value = request.getHeader(key);
				map.put(key, value);
			}
	 
			return map;
		}

}
