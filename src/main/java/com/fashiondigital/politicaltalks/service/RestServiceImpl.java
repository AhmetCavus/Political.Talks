package com.fashiondigital.politicaltalks.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestServiceImpl implements RestService {

	private final WebClient webclient;
	
	public String requestRawString(String url) {
		return this.webclient
	    		.get()
	    		.uri(url)
	    		.retrieve()
	    		.bodyToMono(String.class)
	    		.block();
	}
}