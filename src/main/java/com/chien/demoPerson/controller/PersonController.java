package com.chien.demoPerson.controller;

import com.chien.demoPerson.entity.Person;
import com.chien.demoPerson.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @PostMapping
  public Person create(@RequestBody Person person) {
    return personService.create(person);
  }

  @PutMapping("/{id}")
  public Person update(@PathVariable Long id, @RequestBody Person person) {
    return personService.update(id, person);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    personService.delete(id);
  }

  @GetMapping
  public Iterable<Person> findAll() {
    return personService.findAll();
  }

  @GetMapping("/{id}")
  public Person findById(@PathVariable Long id) {
    return personService.findById(id);
  }
}
