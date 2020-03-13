package com.fashiondigital.politicaltalks.service;

/**
 * A simple service for requesting resources from the web.
 * @author Ahmet Cavus
 */
public interface RestService {
	
	/**
	 * Requests a raw String from the given url.
	 * @param url The address from which the resource should be requested.
	 * @return The resource as a raw string.
	 */
	String requestRawString(String url);
}