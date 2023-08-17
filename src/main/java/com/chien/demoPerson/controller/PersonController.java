package com.chien.demoPerson.controller;

import com.chien.demoPerson.dto.PersonCreationDto;
import com.chien.demoPerson.dto.PersonDto;
import com.chien.demoPerson.dto.PersonUpdateDto;
import com.chien.demoPerson.entity.Person;
import com.chien.demoPerson.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  private PersonService personService;

  @PostMapping
  public ResponseEntity<PersonDto> create(@RequestBody @Validated PersonCreationDto personCreationDto) {
    return ResponseEntity.status(200).body(personService.create(personCreationDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody @Validated PersonUpdateDto personUpdateDto) {
    return ResponseEntity.status(200).body(personService.update(id, personUpdateDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PersonDto> delete(@PathVariable Long id) {
    return ResponseEntity.status(200).body(personService.delete(id));
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
  public ResponseEntity<PersonDto> findById(@PathVariable Long id) {
      return ResponseEntity.status(200).body(personService.findById(id));
  }
  @GetMapping("/name/{name}")
  public ResponseEntity<Iterable<PersonDto>> findByName(@PathVariable String name) {
    return ResponseEntity.status(200).body(personService.findByName(name));
  }
  @GetMapping("/phone/{phone}")
  public ResponseEntity<List<PersonDto>> findByPhone(@PathVariable String phone) {
    return ResponseEntity.status(200).body(personService.findByPhone(phone));
  }
}
