package com.fashiondigital.politicaltalks.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fashiondigital.politicaltalks.entity.TalkEntity;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class TalksService {
	
	RestService restService;
	CsvService<TalkEntity> csvService;
	
	public TalksService(
			RestService restService, 
			CsvService<TalkEntity> csvService) {
		this.restService = restService;
		this.csvService = csvService;
	}
	
	@Async("asyncExecutor")
	public CompletableFuture<List<TalkEntity>> request(String url) throws IOException, CsvRequiredFieldEmptyException {
		var rawTalkData = restService.requestRawString(url);
		var csvPath = csvService.writeCsv("tmp", rawTalkData);
		var listOfTalks = csvService.readCsv(csvPath);
		return CompletableFuture.completedFuture(listOfTalks);
	}
}