package com.fashiondigital.politicaltalks.service;

import lombok.Data;

@Data
public class MostSecurityTestParameter {
	private String subject;
	private String expectedResult;
	
	public MostSecurityTestParameter(String subject, String expectedResult) {
		this.subject = subject;
		this.expectedResult = expectedResult;
	}
}