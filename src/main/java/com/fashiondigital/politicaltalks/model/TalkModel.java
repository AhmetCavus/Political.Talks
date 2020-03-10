package com.fashiondigital.politicaltalks.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TalkModel {

	private String speaker;
	private String subject;
	
	private LocalDate localDate;
	
	private int countOfWords;
	
	public TalkModel(String speaker, String subject, LocalDate localDate, int countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public TalkModel() {}
}
