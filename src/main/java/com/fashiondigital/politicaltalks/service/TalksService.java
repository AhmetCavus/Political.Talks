package com.fashiondigital.politicaltalks.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.fashiondigital.politicaltalks.dto.TalkDto;

@Service
public class TalksService {
	
	RestService restService;
	FileService fileService;
	CsvService<TalkDto> csvService;
	
	public TalksService(
			RestService restService, 
			FileService fileService, 
			CsvService<TalkDto> csvService) {
		this.restService = restService;
		this.fileService = fileService;
		this.csvService = csvService;
	}
	
	@Async("asyncExecutor")
	public CompletableFuture<List<TalkDto>> request(String url) throws IOException {
		var rawTalkData = restService.requestRawString(url);
		var csvPath = fileService.writeFile("tmp", ".csv", rawTalkData);
		var listOfTalks = csvService.parseString(csvPath);
		return CompletableFuture.completedFuture(listOfTalks);
	}
}