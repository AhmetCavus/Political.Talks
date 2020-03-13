package com.fashiondigital.politicaltalks.service;

import java.io.IOException;
import java.util.List;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * A service for loading csv files and converting
 * them to the target type.
 * @author Ahmet Cavus
 * 
 * @param <T>
 */
public interface CsvService<T> {

	/**
	 * Performs reading a csv file.
	 * @param filePath This is the path of the file to be read.
	 * @return a list of the given types.
	 * @throws IOException
	 */
	List<T> readCsv(String filePath) throws IOException;
	
	/**
	 * Performs writing a temporary csv file.
	 * @param filePath This is the path of the file to be write.
	 * @param csvContent
	 * @return the path of the temporary csv file.
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 */
	String writeCsv(String filePath, String csvContent) throws IOException, CsvRequiredFieldEmptyException;
}