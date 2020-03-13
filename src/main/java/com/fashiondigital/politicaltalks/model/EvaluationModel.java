package com.fashiondigital.politicaltalks.model;

import org.springframework.lang.Nullable;

import lombok.Data;

/**
 * Model class to show the result of the evaluation.
 * @author Ahmet Cavus
 *
 */
@Data
public class EvaluationModel {

	/**
	 * Holding the answer for the question:
	 * Which politician has made the most speeches.
	 */
	private @Nullable String mostSpeeches;
	
	/**
	 * Holding the answer for the question:
	 * Which politician has made the speeches about security.
	 */
	private @Nullable String mostSecurity;
	
	/**
	 * Holding the answer for the question:
	 * Which politician has the least number of spoken words.
	 */
	private @Nullable String leastWordy;
	
	public EvaluationModel(@Nullable String mostSpeeches, @Nullable String mostSecurity, @Nullable String leastWordy) {
		this.mostSpeeches = mostSpeeches;
		this.mostSecurity = mostSecurity;
		this.leastWordy = leastWordy;
	}
	
	public EvaluationModel() {}
	
}
