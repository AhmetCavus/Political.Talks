package com.fashiondigital.politicaltalks.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.modelmapper.TypeMap;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.model.TalkModel;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import lombok.RequiredArgsConstructor;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
public class TalksServiceImpl implements TalksService {
	
	private final RestService restService;
	private final CsvService<TalkDto> csvService;
	private final TypeMap<TalkDto, TalkModel> mapper;
	
	/**
	 * {@inheritDoc}
	 */
	@Async("asyncExecutor")
	public CompletableFuture<List<TalkDto>> request(String url) throws IOException, CsvRequiredFieldEmptyException {
		var rawTalkData = restService.requestRawString(url);
		var csvPath = csvService.writeCsv("tmp", rawTalkData);
		var listOfTalks = csvService.readCsv(csvPath);
		return CompletableFuture.completedFuture(listOfTalks);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TalkModel> convertToModels(List<TalkDto> talks) {
		return
			talks
			.stream()
			.map(this::convertToModel)
			.collect(Collectors.toList());
	}
	
	private TalkModel convertToModel(TalkDto talkDto) {
	    return mapper.map(talkDto);
	}
}