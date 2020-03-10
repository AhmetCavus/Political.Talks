package com.fashiondigital.politicaltalks.model;

import org.springframework.lang.Nullable;
import lombok.Data;

@Data
public class ExceptionModel {

	private @Nullable String message;
	
	public ExceptionModel(@Nullable String message) {
		this.message = message;
	}
	
	public ExceptionModel() {}	
}