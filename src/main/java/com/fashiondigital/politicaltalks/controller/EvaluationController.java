package com.fashiondigital.politicaltalks.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fashiondigital.politicaltalks.dto.TalkDto;
import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.ExceptionModel;
import com.fashiondigital.politicaltalks.model.TalkModel;
import com.fashiondigital.politicaltalks.service.EvaluationService;
import com.fashiondigital.politicaltalks.service.TalksService;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired TypeMap<TalkDto, TalkModel> mapper;
	@Autowired TalksService talksService;
	@Autowired EvaluationService evaluationService;
	
	@GetMapping
    public DeferredResult<EvaluationModel> evaluationAction(@NotBlank @RequestParam String url1, @RequestParam(required = false) @Nullable String url2) throws InterruptedException, ExecutionException, IOException {
		var deferredResult = new DeferredResult<EvaluationModel>();
		handleEvaluationRequest(url1, url2, deferredResult);
		return deferredResult;
    }

	private void handleEvaluationRequest(String url1, String url2, DeferredResult<EvaluationModel> deferredResult)
			throws IOException, InterruptedException, ExecutionException {
		var talksFromUrl1 = talksService.request(url1);
		if(StringUtils.isBlank(url2)) {
			talksFromUrl1
				.thenAcceptAsync(talkDtos -> onEvaluate(talkDtos, deferredResult));
		} else {
			var talksFromUrl2 = talksService.request(url2);
			CompletableFuture
				.allOf(talksFromUrl1, talksFromUrl2)
				.join();
			var combinedTalks = talksFromUrl1.get();
			combinedTalks.addAll(talksFromUrl2.get());
			onEvaluate(combinedTalks, deferredResult);
		}
	}
	
	private void onEvaluate(List<TalkDto> talkDtos, DeferredResult<EvaluationModel> deferredResult) {
		var talks =
			talkDtos
			.stream()
			.map(this::convertToModel)
			.collect(Collectors.toList());
		
		deferredResult.setResult(evaluationService.evaluate(talks));
	}
	
	private TalkModel convertToModel(TalkDto talkDto) {
	    return mapper.map(talkDto);
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionModel HandleException(@NotNull Exception ex) { return new ExceptionModel(ex.getMessage()); }
}