package com.fashiondigital.politicaltalks.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fashiondigital.politicaltalks.model.EvaluationModel;
import com.fashiondigital.politicaltalks.model.ExceptionModel;
import com.fashiondigital.politicaltalks.service.EvaluationService;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

	@Autowired EvaluationService evaluationService;
	
	@GetMapping
    public EvaluationModel evaluationAction(@RequestParam String url1, @RequestParam(required = false) @Nullable String url2) {
		return new EvaluationModel();
    }
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionModel HandleException(@NotNull Exception ex) { return new ExceptionModel(ex.getMessage()); }
}