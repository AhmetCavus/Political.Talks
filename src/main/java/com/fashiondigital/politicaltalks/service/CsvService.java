package com.fashiondigital.politicaltalks.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CsvService<T> {

	private final String csvHeader;
	private final HeaderColumnNameMappingStrategy<T> strategy;
	
	public CsvService(String csvHeader, HeaderColumnNameMappingStrategy<T> mappingStrategy) {
		this.strategy = mappingStrategy;
		this.csvHeader = csvHeader;
	}
	
	public List<T> readCsv(String filePath) throws IOException {
		Path csvPath = Paths.get(filePath);
	    try (BufferedReader bufferedReader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
	        var csvToBean = new CsvToBeanBuilder<T>(bufferedReader)
	        		.withMappingStrategy(strategy)
	                .build();
	        return csvToBean.parse();
	    }
	}
	
	public String writeCsv(String filePath, String csvContent) throws IOException, CsvRequiredFieldEmptyException {
		File tmpFile = File.createTempFile(filePath, "csv");
	    FileWriter writer = new FileWriter(tmpFile);
	    var newCsvContent = replaceHeaderOfContent(csvContent, csvHeader);
	    writer.write(newCsvContent);
	    writer.close();
	    return tmpFile.getPath();
	}
	
	private String replaceHeaderOfContent(String csvContent, String csvHeader) {
		var csvWithoutHeader = StringUtils.substringAfter(csvContent, StringUtils.LF);
    	return StringUtils.join(csvHeader, StringUtils.LF, csvWithoutHeader);
	}
}