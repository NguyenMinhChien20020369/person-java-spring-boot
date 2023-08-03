package com.chien.demoPerson.controller;

import com.chien.demoPerson.dto.PersonDto;
import com.chien.demoPerson.entity.Person;
import com.chien.demoPerson.exception.AppException;
import com.chien.demoPerson.service.PersonService;
import jakarta.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  private PersonService personService;

  @PostMapping
  public Person create(@RequestBody @Validated PersonDto personDto) {
    return personService.create(personDto);
  }

  @PutMapping("/{id}")
  public Person update(@PathVariable Long id, @RequestBody Person person) {
    return personService.update(id, person);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    personService.delete(id);
  }
  @GetMapping("/home")
  public String homepage() {
    return "index";
  }
  @GetMapping
  @CrossOrigin(origins = {"http://example.com"}, methods = {RequestMethod.PUT})
  public ResponseEntity<Iterable<PersonDto>> findAll() {
    return ResponseEntity.status(200).body(personService.findAll());
  }

  @GetMapping("/{id}")
  public PersonDto findById(@PathVariable Long id) {
      return personService.findById(id);
  }
  @GetMapping("/name/{name}")
  public List<Person> findByName(@PathVariable String name) {
    return personService.findByName(name);
  }
  @GetMapping("/phone/{phone}")
  public List<Person> findByPhone(@PathVariable String phone) {
    return personService.findByPhone(phone);
  }
}
