package com.fashiondigital.politicaltalks.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fashiondigital.politicaltalks.dto.SpeechDto;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

@Service
public class CsvService<T> {

	@Async("asyncExecutor")
	public CompletableFuture<List<SpeechDto>> parseString(String fileName) throws IOException {
		Path myPath = Paths.get(fileName);
	    try (BufferedReader br = Files.newBufferedReader(myPath,
	            StandardCharsets.UTF_8)) {
	
	    	HeaderColumnNameMappingStrategy<SpeechDto> strategy
	        = new HeaderColumnNameMappingStrategy<>();
	    	strategy.setType(SpeechDto.class);
	    	
	        var csvToBean = new CsvToBeanBuilder<SpeechDto>(br)
	        		.withMappingStrategy(strategy)
	                .build();
	
	        return CompletableFuture.completedFuture(csvToBean.parse());
	    }
	}
	
}
