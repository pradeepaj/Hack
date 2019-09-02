package com.ing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * For throwing User exception messages in Maggie application
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 * @author pradeep
	 */
	@ExceptionHandler(EnterValidCredentials.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(EnterValidCredentials exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setStatus("FAILURE");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}