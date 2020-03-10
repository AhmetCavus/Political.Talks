package com.fashiondigital.politicaltalks.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class EvaluationModel {

	private @Nullable String mostSpeeches;
	private @Nullable String mostSecurity;
	private @Nullable String leastWordy;
	
	public EvaluationModel(@Nullable String mostSpeeches, @Nullable String mostSecurity, @Nullable String leastWordy) {
		this.mostSpeeches = mostSpeeches;
		this.mostSecurity = mostSecurity;
		this.leastWordy = leastWordy;
	}
	
	public EvaluationModel() {}
	
}
