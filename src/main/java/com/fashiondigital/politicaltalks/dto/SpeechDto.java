package com.fashiondigital.politicaltalks.dto;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class SpeechDto {

	@CsvBindByName(column = "Redner")
	private String speaker;
	
	@CsvBindByName(column = "Thema")
	private String subject;
	
	@CsvBindByName(column = "Datum")
	private String localDate;
	
	@CsvBindByName(column = "Wörter")
	private Long countOfWords;
	
	public SpeechDto(String speaker, String subject, String localDate, Long countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public SpeechDto() {}
	
}
