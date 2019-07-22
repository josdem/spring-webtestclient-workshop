package com.jos.dem.spring.webflux.webclient.service.impl;

import reactor.core.publisher.Mono;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.beans.factory.annotation.Autowired;
import com.jos.dem.spring.webflux.webclient.service.WebfluxService;

public class WebfluxServiceImpl implements WebfluxService {

  @Autowired
  private WebClient webClient;

  public Mono<String> getGreetings(){
    return webClient.get()
      .uri("/")
      .retrieve()
    .bodyToMono(String.class);
  }

  public Mono<HttpHeaders> getHeaders(){
    return webClient.get()
				.uri("/")
				.exchange()
				.map(response -> response.headers().asHttpHeaders());
  }

}
