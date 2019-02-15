package com.springbootswagger.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
	

	@ExceptionHandler(ProductException.class)
	public ErrorResponse exceptionProductHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return error;
		
	}

	@ExceptionHandler(Exception.class)
	public ErrorResponse exceptionHandler(Exception ex) {
		
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage("The request could not be understood by the server due to malformed syntax.");
		return error;
	}
}
