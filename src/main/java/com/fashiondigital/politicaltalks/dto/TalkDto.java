package com.fashiondigital.politicaltalks.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class TalkDto {

	@CsvBindByName(column = "speaker")
	@CsvBindByPosition(position = 0)
	private String speaker;
	
	@CsvBindByName(column = "subject")
	@CsvBindByPosition(position = 1)
	private String subject;
	
	@CsvBindByName(column = "localDate")
	@CsvBindByPosition(position = 2)
	private String localDate;
	
	@CsvBindByName(column = "countOfWords")
	@CsvBindByPosition(position = 3)
	private int countOfWords;
	
	public TalkDto(String speaker, String subject, String localDate, int countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public TalkDto() {}
}
