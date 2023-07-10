package com.chien.demoPerson.service.impl;

import com.chien.demoPerson.entity.Person;
import com.chien.demoPerson.repository.PersonRepository;
import com.chien.demoPerson.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonRepository personRepository;

  @Override
  public Person create(Person person) {
    if (person.getEmail() == null || person.getEmail().isEmpty()) {
      return null;
    }
    if (person.getName() == null || person.getName().isEmpty()) {
      return null;
    }
    return personRepository.save(person);
  }

  @Override
  public Person update(Long id, Person person) {
    Person fromDB = personRepository.findById(id).orElse(null);
    if (fromDB == null) {
      return null;
    }
    fromDB.setEmail(person.getEmail());
    fromDB.setName(person.getName());
    return personRepository.save(fromDB);
  }

  @Override
  public void delete(Long id) {
    personRepository.deleteById(id);
  }

  @Override
  public Person findById(Long id) {
    return personRepository.findById(id).orElse(null);
  }

  @Override
  public Iterable<Person> findAll() {
    return personRepository.findAll();
  }
}
