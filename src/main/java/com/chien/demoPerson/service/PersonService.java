package com.chien.demoPerson.service;

import com.chien.demoPerson.entity.Person;

public interface PersonService {

  Person create(Person person);

  Person update(Long id, Person person);

  void delete(Long id);

  Person findById(Long id);

  Iterable<Person> findAll();
}
