package com.fashiondigital.politicaltalks.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.model.TalkModel;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * A service for retrieving speeches from the given source.
 * @author Ahmet Cavus
 *
 */
public interface TalksService {
	
	/**
	 * Retrieves the speeches as a list of items from type {@link TalkDto}.
	 * @param url The source of the items.
	 * @return the speeches from the source.
	 * @throws IOException
	 * @throws CsvRequiredFieldEmptyException
	 */
	CompletableFuture<List<TalkDto>> request(String url) throws IOException, CsvRequiredFieldEmptyException;
	
	/**
	 * Converts the dto list to a list of items from type {@link TalkModel}.
	 * @param talks The dtos to be converted.
	 * @return The the list of models.
	 */
	List<TalkModel> convertToModels(List<TalkDto> talks);
}