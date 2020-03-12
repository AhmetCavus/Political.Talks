package com.fashiondigital.politicaltalks.service;

import lombok.Data;

@Data
public class MostSpeechesTestParameter {
	private int yearOfSpeak;
	private String expectedResult;
	
	public MostSpeechesTestParameter(int yearOfSpeak, String expectedResult) {
		this.yearOfSpeak = yearOfSpeak;
		this.expectedResult = expectedResult;
	}
}