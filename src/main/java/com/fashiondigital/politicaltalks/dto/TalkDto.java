package com.fashiondigital.politicaltalks.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class TalkDto {

	@CsvBindByName(column = "Redner")
	private String speaker;
	
	@CsvBindByName(column = "Thema")
	private String subject;
	
	@CsvBindByName(column = "Datum")
	private String localDate;
	
	@CsvBindByName(column = "Wörter")
	private int countOfWords;
	
	public TalkDto(String speaker, String subject, String localDate, int countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public TalkDto() {}
	
}
