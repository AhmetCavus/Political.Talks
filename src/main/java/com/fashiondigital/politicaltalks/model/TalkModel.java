package com.fashiondigital.politicaltalks.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TalkModel {

	private String speaker;
	private String subject;
	private LocalDateTime localDate;
	private Long countOfWords;
	
	public TalkModel(String speaker, String subject, LocalDateTime localDate, Long countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public TalkModel() {}
	
}
