package com.chien.demoPerson.service;

import com.chien.demoPerson.dto.PersonDto;
import com.chien.demoPerson.entity.Person;
import java.util.List;

public interface PersonService {

  Person create(PersonDto personDto);

  Person update(Long id, Person person);

  void delete(Long id);

  Person findById(Long id);
  List<Person> findByName(String name);
  List<Person> findByPhone(String phone);

  Iterable<PersonDto> findAll();
}
