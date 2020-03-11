package com.fashiondigital.politicaltalks.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class RestApiError {

	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private String logMessage;

	public RestApiError(HttpStatus status, String message, Throwable ex) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.logMessage = ex.getMessage();
	}
}