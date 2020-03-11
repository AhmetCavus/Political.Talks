package com.fashiondigital.politicaltalks.configuration;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfiguration {

	@Bean
	public UrlValidator createUrlValidator() {
		String[] schemes = {"http","https"}; 
		return new UrlValidator(schemes);
	}
}