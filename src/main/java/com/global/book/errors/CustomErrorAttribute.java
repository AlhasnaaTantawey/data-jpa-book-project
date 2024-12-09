package com.global.book.errors;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class CustomErrorAttribute extends DefaultErrorAttributes {

	
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		
		Map<String, Object>  errorAttributes  =  super.getErrorAttributes(webRequest, options);
		
		errorAttributes.put("locale", webRequest.getLocale().toString());
		errorAttributes.put("sucess", Boolean.FALSE);
		errorAttributes.put("status",errorAttributes.get("error"));
		errorAttributes.put("exception", errorAttributes.get("message"));
		errorAttributes.put("details", Collections.singletonList(errorAttributes.get("message")));
		
		errorAttributes.remove("error");
		errorAttributes.remove("path");
		
		return errorAttributes;
		
	}

	
	

	
}
