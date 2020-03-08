package com.fashiondigital.politicaltalks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Component
public class WebClientConfiguration
{
	@Bean
	public WebClient defaultWebClient() {
	    var tcpClient = TcpClient.create()
	      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2_000)
	      .doOnConnected(connection ->
	        connection.addHandlerLast(new ReadTimeoutHandler(2))
	          .addHandlerLast(new WriteTimeoutHandler(2)));
	    return WebClient.builder()
	      .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
	      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_ATOM_XML_VALUE)
	      .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_ATOM_XML_VALUE)
	      .build();
	}
}