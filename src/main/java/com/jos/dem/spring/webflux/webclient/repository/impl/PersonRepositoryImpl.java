package com.jos.dem.spring.webflux.webclient.repository.impl;

import java.util.Map;
import java.util.HashMap;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Service;

import com.jos.dem.spring.webflux.webclient.model.Person;
import com.jos.dem.spring.webflux.webclient.repository.PersonRepository;

@Service
public class PersonRepositoryImpl implements PersonRepository {

  private Map<String, Person> persons = new HashMap<>();

  public void save(Person person){
    persons.put(person.getNickname(), person);
  }

  public Flux<Person> findAll(){
    return Flux.fromIterable(persons.values());
  }

  public Mono<Person> findByNickname(String nickname){
    return Mono.just(persons.get(nickname));
  }

}
