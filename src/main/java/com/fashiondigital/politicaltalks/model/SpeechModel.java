package com.fashiondigital.politicaltalks.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SpeechModel {

	private String speaker;
	private String subject;
	private LocalDateTime localDate;
	private Long countOfWords;
	
	public SpeechModel(String speaker, String subject, LocalDateTime localDate, Long countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public SpeechModel() {}
	
}
