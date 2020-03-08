package com.fashiondigital.politicaltalks.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SpeechDto {

	private String speaker;
	private String subject;
	private LocalDateTime localDate;
	private Long countOfWords;
	
	public SpeechDto(String speaker, String subject, LocalDateTime localDate, Long countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public SpeechDto() {}
	
}
