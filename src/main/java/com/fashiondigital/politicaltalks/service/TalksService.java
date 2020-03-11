package com.fashiondigital.politicaltalks.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.model.TalkModel;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface TalksService {
	CompletableFuture<List<TalkDto>> request(String url) throws IOException, CsvRequiredFieldEmptyException;
	List<TalkModel> convertToModels(List<TalkDto> talks);
}