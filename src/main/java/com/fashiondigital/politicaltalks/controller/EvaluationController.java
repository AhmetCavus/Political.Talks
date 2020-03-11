package com.fashiondigital.politicaltalks.controller;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fashiondigital.politicaltalks.entity.TalkEntity;
import com.fashiondigital.politicaltalks.exception.InvalidUrlException;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.TalkModel;
import com.fashiondigital.politicaltalks.service.EvaluationService;
import com.fashiondigital.politicaltalks.service.TalksService;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired EvaluationService evaluationService;
	@Autowired TalksService talksService;
	@Autowired TypeMap<TalkEntity, TalkModel> mapper;
	@Autowired UrlValidator urlValidator;
	
	@GetMapping
    public DeferredResult<EvaluationModel> evaluationAction(@NotBlank @RequestParam String url1, @RequestParam(required = false) @Nullable String url2) 
    		throws 
    		InterruptedException, 
    		ExecutionException, 
    		IOException, 
    		CsvRequiredFieldEmptyException, 
    		InvalidUrlException {
		var deferredResult = new DeferredResult<EvaluationModel>();
		handleEvaluationRequest(url1, url2, deferredResult);
		return deferredResult;
    }

	private void handleEvaluationRequest(String url1, String url2, DeferredResult<EvaluationModel> deferredResult)
			throws 
			IOException, 
			InterruptedException, 
			ExecutionException, 
			CsvRequiredFieldEmptyException, 
			InvalidUrlException {
		if(!urlValidator.isValid(url1)) throw new InvalidUrlException("The url1 is not valid");
		var talksFromUrl1 = talksService.request(url1);
		if(StringUtils.isBlank(url2)) {
			talksFromUrl1
				.thenAcceptAsync(talkDtos -> onEvaluate(talkDtos, deferredResult));
		} else {
			if(!urlValidator.isValid(url2)) throw new InvalidUrlException("The url2 is not valid");
			var talksFromUrl2 = talksService.request(url2);
			CompletableFuture
				.allOf(talksFromUrl1, talksFromUrl2)
				.join();
			var combinedTalks = talksFromUrl1.get();
			combinedTalks.addAll(talksFromUrl2.get());
			onEvaluate(combinedTalks, deferredResult);
		}
	}
	
	private void onEvaluate(List<TalkEntity> talkDtos, DeferredResult<EvaluationModel> deferredResult) {
		var talks =
			talkDtos
			.stream()
			.map(this::convertToModel)
			.collect(Collectors.toList());
		
		deferredResult.setResult(evaluationService.evaluate(talks));
	}
	
	private TalkModel convertToModel(TalkEntity talkDto) {
	    return mapper.map(talkDto);
	}
}