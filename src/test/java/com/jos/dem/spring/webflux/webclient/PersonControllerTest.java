package com.jos.dem.spring.webflux.webclient;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.jos.dem.spring.webflux.webclient.model.Person;
import com.jos.dem.spring.webflux.webclient.service.WebclientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private WebclientService webclientService;

  @Test
  public void shouldGetAllPersons() throws Exception {
    log.info("Running: Should get persons at {}", new Date());
    List<Person> persons = webclientService.getAll().collectList().block();
    assertEquals(5, persons.size(), "Should have the right size") ;

    assertAll("person",
      () -> assertTrue(persons.contains(new Person("josdem", "joseluis.delacruz@gmail.com"))),
      () -> assertTrue(persons.contains(new Person("tgrip", "tgrip@email.com"))),
      () -> assertTrue(persons.contains(new Person("edzero", "edzero@email.com"))),
      () -> assertTrue(persons.contains(new Person("siedrix", "siedrix@email.com"))),
      () -> assertTrue(persons.contains(new Person("mkheck", "mkheck@losheckler.com")))
    );
  }

  @Test
  public void shouldGetPerson() throws Exception {
    log.info("Running: I validate person data at {}", new Date());

    String nickname = "josdem";

    Person person = webclientService.getPerson(nickname).block();
    assertAll("person",
      () -> assertEquals(nickname, person.getNickname()),
      () -> assertEquals("joseluis.delacruz@gmail.com", person.getEmail())
    );
  }

}