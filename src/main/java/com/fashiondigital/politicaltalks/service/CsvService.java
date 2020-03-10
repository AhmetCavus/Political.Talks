package com.fashiondigital.politicaltalks.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

@Service
public class CsvService<T> {

	public List<TalkDto> parseString(String fileName) throws IOException {
		Path myPath = Paths.get(fileName);
	    try (BufferedReader br = Files.newBufferedReader(myPath,
	            StandardCharsets.UTF_8)) {
	
	    	HeaderColumnNameMappingStrategy<TalkDto> strategy
	        = new HeaderColumnNameMappingStrategy<>();
	    	strategy.setType(TalkDto.class);
	    	
	        var csvToBean = new CsvToBeanBuilder<TalkDto>(br)
	        		.withMappingStrategy(strategy)
	                .build();
	
	        return csvToBean.parse();
	    }
	}
	
}