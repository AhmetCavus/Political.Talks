package com.fashiondigital.politicaltalks.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RestService {

	private WebClient webclient;
	
	public RestService(WebClient webclient) {
		this.webclient = webclient;
	}
	
	public String requestRawString(String url) {
		return this.webclient
	    		.get()
	    		.uri(url)
	    		.retrieve()
	    		.bodyToMono(String.class)
	    		.block();
	}
}