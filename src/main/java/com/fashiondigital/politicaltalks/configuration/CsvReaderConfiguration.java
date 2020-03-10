package com.fashiondigital.politicaltalks.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fashiondigital.politicaltalks.entity.TalkEntity;
import com.fashiondigital.politicaltalks.service.CsvService;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

@Configuration
public class CsvReaderConfiguration
{
	@Bean
	public TalkEntity createEntity() {
		return new TalkEntity();
	}
	
	@Bean
	public HeaderColumnNameMappingStrategy<TalkEntity> createMappingStrategy() {
    	HeaderColumnNameMappingStrategy<TalkEntity> strategy = new HeaderColumnNameMappingStrategy<>();
    	strategy.setType(TalkEntity.class);
    	return strategy;
	}
	
	@Bean
	public CsvService<TalkEntity> createCsvService(@Qualifier("csvHeader") String csvHeader, HeaderColumnNameMappingStrategy<TalkEntity> mappingStrategy) {
		return new CsvService<TalkEntity>(csvHeader, mappingStrategy);
	}
	
	@Bean(name = "csvHeader")
	public String CreateCsvHeader() {
		return "speaker, subject, localDate, countOfWords";
	}
}