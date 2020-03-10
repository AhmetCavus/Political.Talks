package com.fashiondigital.politicaltalks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EvaluationConfiguration
{
	@Bean("querySubject")
	public String createQuerySubject() {
		return "Innere Sicherheit";
	}
	
	@Bean("queryYear")
	public int createQueryYear() {
    	return 2013;
	}
}