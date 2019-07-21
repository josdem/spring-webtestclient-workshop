package com.jos.dem.spring.webflux.webclient;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebclientTest {

  @Autowired
  private WebTestClient webClient;

  @Test
  public void shouldGetHelloWorld() throws Exception {
    webClient.get().uri("/").accept(APPLICATION_JSON)
      .exchange()
      .expectStatus().isOk()
      .expectHeader().contentType(APPLICATION_JSON_UTF8)
      .expectBody(String.class);
  }

}