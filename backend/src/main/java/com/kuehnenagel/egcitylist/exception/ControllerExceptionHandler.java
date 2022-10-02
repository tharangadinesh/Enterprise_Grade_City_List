package com.kuehnenagel.egcitylist.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kuehnenagel.egcitylist.payload.response.ErrorMessageResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessageResponse> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorMessageResponse message = new ErrorMessageResponse(
				HttpStatus.NOT_FOUND.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessageResponse> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessageResponse message = new ErrorMessageResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}