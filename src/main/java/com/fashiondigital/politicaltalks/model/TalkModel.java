package com.fashiondigital.politicaltalks.model;

import java.time.LocalDate;
import lombok.Data;

/**
 * A model for the representation of the csv schema.
 * @author Ahmet Cavus
 *
 */
@Data
public class TalkModel {

	/**
	 * The speaker from the csv data.
	 */
	private String speaker;
	
	/**
	 * The subject from the csv data.
	 */
	private String subject;
	
	/**
	 * The datetime from the csv data.
	 */
	private LocalDate localDate;
	
	/**
	 * The count of words from the csv data.
	 */
	private int countOfWords;
	
	public TalkModel(String speaker, String subject, LocalDate localDate, int countOfWords) {
		this.speaker = speaker;
		this.subject = subject;
		this.localDate = localDate;
		this.countOfWords = countOfWords;
	}
	
	public TalkModel() {}
}
