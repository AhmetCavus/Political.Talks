package com.fashiondigital.politicaltalks.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fashiondigital.politicaltalks.model.TalkModel;
import com.fasterxml.jackson.databind.util.ClassUtil.Ctor;

@Service
public class TalksService {

	private final WebClient webclient;
	
	public TalksService(WebClient webclient) {
		this.webclient = webclient;
	}
	
    public TalkModel request(String ur)
    {
    	return null;
    }
	
}