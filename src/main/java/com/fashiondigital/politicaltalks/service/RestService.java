package com.fashiondigital.politicaltalks.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RestService {

	private WebClient webclient;
	
	public RestService(WebClient webclient) {
		this.webclient = webclient;
	}
	
	@Async("asyncExecutor")
	public CompletableFuture<String> requestRawString(String url) {
		var result =
		this.webclient
    		.get()
    		.uri(url)
    		.retrieve()
    		.bodyToMono(String.class)
    		.block();
		return CompletableFuture.completedFuture(result);
	}
}