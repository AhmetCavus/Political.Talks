package com.fashiondigital.politicaltalks.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.exception.InvalidUrlException;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.service.EvaluationService;
import com.fashiondigital.politicaltalks.service.TalksService;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired EvaluationService evaluationService;
	@Autowired TalksService talksService;
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

	private void handleEvaluationRequest(String mainUrl, String optionalUrl, DeferredResult<EvaluationModel> deferredResult)
			throws 
			IOException, 
			InterruptedException, 
			ExecutionException, 
			CsvRequiredFieldEmptyException, 
			InvalidUrlException {
        if(!urlValidator.isValid(mainUrl)) throw new InvalidUrlException("The url1 is not valid");
        var talksFromMainUrl = talksService.request(mainUrl);
        if(StringUtils.isBlank(optionalUrl)) {
            talksFromMainUrl
                .thenAcceptAsync(talkDtos -> onEvaluate(talkDtos, deferredResult));
        } else {
            if(!urlValidator.isValid(optionalUrl)) throw new InvalidUrlException("The url2 is not valid");
            var talksFromOptionalUrl = talksService.request(optionalUrl);
            
            CompletableFuture
            .allOf(talksFromMainUrl, talksFromOptionalUrl)
            .join();
	        
            var totalTalks = talksFromMainUrl.get();
	        totalTalks.addAll(talksFromOptionalUrl.get());
	        onEvaluate(totalTalks, deferredResult);
        }
	}
	
	private void onEvaluate(List<TalkDto> talkDtos, DeferredResult<EvaluationModel> deferredResult) {
		var talkModels = talksService.convertToModels(talkDtos);
		deferredResult.setResult(evaluationService.evaluate(talkModels));
	}
}