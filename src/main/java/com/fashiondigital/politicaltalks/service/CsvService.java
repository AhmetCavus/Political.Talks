package com.fashiondigital.politicaltalks.service;

import java.io.IOException;
import java.util.List;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface CsvService<T> {

	List<T> readCsv(String filePath) throws IOException;
	
	String writeCsv(String filePath, String csvContent) throws IOException, CsvRequiredFieldEmptyException;
}