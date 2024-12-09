package com.global.book.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
   
	Logger log =LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                              HttpHeaders headers,
	                                                              HttpStatusCode status,
	                                                              WebRequest request) {
	    List<String> errors = new ArrayList<>();

	    // Log and collect detailed validation errors for each field
	    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	        String errorMessage = "Field: " + fieldError.getField() + " - Rejected value: [" + 
	                              fieldError.getRejectedValue() + "] - Error: " + fieldError.getDefaultMessage();
	        System.out.println("Field Error: " + errorMessage);  // Log error details for debugging
	        errors.add(errorMessage);
	    }

	    for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
	        String errorMessage = "Object: " + objectError.getObjectName() + " - Error: " + objectError.getDefaultMessage();
	        System.out.println("Global Error: " + errorMessage);  // Log error details for debugging
	        errors.add(errorMessage);
	    }

	    ErrorResponse errorResponse = new ErrorResponse("Validation failed", errors);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}



	@ExceptionHandler(RecordNotFoundException.class)
	  public ResponseEntity<?>    handleRecordNotFound(RecordNotFoundException ex){
		  
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), Collections.singletonList(ex.getMessage()));
		  return ResponseEntity.
				  status(HttpStatus.NOT_FOUND).
				  body(error);
	  }
	
	
	
	@ExceptionHandler(DuplicateRecordException.class)
	  public ResponseEntity<?> handleDuplicateRecord(DuplicateRecordException ex){
		  
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), Collections.singletonList(ex.getMessage()));
		  return ResponseEntity.
				  status(HttpStatus.BAD_REQUEST).
				  body(error);
	  }
	
	
	@ExceptionHandler(Exception.class)
	  public ResponseEntity<?> handleAllException (Exception ex ,  WebRequest request){
		  
		ex.printStackTrace();
		log.error(ex.getMessage());
		ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), Collections.singletonList(ex.getMessage()));
		  return ResponseEntity.
				  status(HttpStatus.EXPECTATION_FAILED).
				  body(error);
	  }
}
