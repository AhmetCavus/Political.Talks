package com.fashiondigital.politicaltalks.controller;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.concurrent.Computable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fashiondigital.politicaltalks.dto.SpeechDto;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.ExceptionModel;
import com.fashiondigital.politicaltalks.service.CsvService;
import com.fashiondigital.politicaltalks.service.EvaluationService;
import com.fashiondigital.politicaltalks.service.FileService;
import com.fashiondigital.politicaltalks.service.RestService;
import com.fashiondigital.politicaltalks.service.TalksService;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired EvaluationService evaluationService;
	@Autowired TalksService talksService;
	@Autowired RestService restService;
	@Autowired FileService fileService;
	@Autowired CsvService<SpeechDto> csvService;
	
	@GetMapping
    public EvaluationModel evaluationAction(@RequestParam String url1, @RequestParam(required = false) @Nullable String url2) throws InterruptedException, ExecutionException, IOException {
		var rawTalkData = restService.requestRawString(url1);
		CompletableFuture.allOf(rawTalkData).join();
		
		var csvPath1 = fileService.writeFile("url1", ".csv", rawTalkData.get());
		CompletableFuture.allOf(csvPath1).join();
		
		var listOfTalks = csvService.parseString(csvPath1.get());
		CompletableFuture.allOf(listOfTalks).join();
		
		var result = listOfTalks.get();
		Logger.getGlobal().log(Level.ALL, result.toString());
		
		return new EvaluationModel();
    }
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionModel HandleException(@NotNull Exception ex) { return new ExceptionModel(ex.getMessage()); }
}