package com.fashiondigital.politicaltalks.entity;

import lombok.Data;

@Data
public class TalkEntity {

	private String speaker;
	
	private String subject;
	
	private String localDate;
	
	private int countOfWords;
	
	public TalkEntity(String speaker, String subject, String localDate, int countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public TalkEntity() {}
}
