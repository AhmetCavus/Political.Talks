package com.fashiondigital.politicaltalks.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

	// TODO Improve error handling here
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        var error = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Server error", ex);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(InvalidUrlException.class)
    public final ResponseEntity<Object> handleInvalidUrlExceptions(Exception ex, WebRequest request) {
        var error = new RestApiError(HttpStatus.BAD_REQUEST, "You have submitted a non-valid url", ex);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var error = new RestApiError(HttpStatus.BAD_REQUEST, "Validation Failed", ex);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var error = new RestApiError(HttpStatus.BAD_REQUEST, "Validation Failed", ex);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}