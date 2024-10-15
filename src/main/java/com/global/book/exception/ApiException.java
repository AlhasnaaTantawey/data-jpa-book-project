package com.global.book.exception;

import lombok.Data;
import java.time.ZonedDateTime;


public class ApiException {

    private String errorMessage;
    private Integer statusCode;
    private ZonedDateTime zonedDateTime;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public ZonedDateTime getZonedDateTime() {
		return zonedDateTime;
	}
	public void setZonedDateTime(ZonedDateTime zonedDateTime) {
		this.zonedDateTime = zonedDateTime;
	}
    
    
    
}