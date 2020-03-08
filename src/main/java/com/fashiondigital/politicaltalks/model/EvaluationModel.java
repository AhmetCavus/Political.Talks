package com.fashiondigital.politicaltalks.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class EvaluationModel {

	private @Nullable String mostSpeeched;
	private @Nullable String mostSecurity;
	private @Nullable String leastWordy;
	
	public EvaluationModel(@Nullable String mostSpeeched, @Nullable String mostSecurity, @Nullable String leastWordy) {
		this.mostSpeeched = mostSpeeched;
		this.mostSecurity = mostSecurity;
		this.leastWordy = leastWordy;
	}
	
	public EvaluationModel() {}
	
}
