package com.fashiondigital.politicaltalks.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.service.CsvService;
import com.fashiondigital.politicaltalks.service.CsvServiceImpl;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

@Configuration
public class CsvReaderConfiguration
{
	@Bean
	public TalkDto createDto() {
		return new TalkDto();
	}
	
	@Bean
	public HeaderColumnNameMappingStrategy<TalkDto> createMappingStrategy() {
    	HeaderColumnNameMappingStrategy<TalkDto> strategy = new HeaderColumnNameMappingStrategy<>();
    	strategy.setType(TalkDto.class);
    	return strategy;
	}
	
	@Bean
	public CsvService<TalkDto> createCsvService(@Qualifier("csvHeader") String csvHeader, HeaderColumnNameMappingStrategy<TalkDto> mappingStrategy) {
		return new CsvServiceImpl<TalkDto>(csvHeader, mappingStrategy);
	}
	
	@Bean(name = "csvHeader")
	public String CreateCsvHeader() {
		return "speaker, subject, localDate, countOfWords";
	}
}