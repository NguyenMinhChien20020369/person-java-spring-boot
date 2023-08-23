package com.chien.demoPerson.controller;

import com.chien.demoPerson.dto.GroupOfPeopleCreationDto;
import com.chien.demoPerson.dto.GroupOfPeopleDto;
import com.chien.demoPerson.entity.GroupOfPeople;
import com.chien.demoPerson.service.GroupOfPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupOfPeopleController {

  @Autowired
  private GroupOfPeopleService groupOfPeopleService;

  @PostMapping
  public ResponseEntity<GroupOfPeopleDto> create(@RequestBody @Validated GroupOfPeopleCreationDto groupOfPeopleCreationDto) {
    return ResponseEntity.status(200).body(groupOfPeopleService.create(groupOfPeopleCreationDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<GroupOfPeopleDto> update(@PathVariable Long id, @RequestBody @Validated GroupOfPeopleCreationDto groupOfPeopleCreationDto) {
    return ResponseEntity.status(200).body(groupOfPeopleService.update(id, groupOfPeopleCreationDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<GroupOfPeopleDto> delete(@PathVariable Long id) {
    return ResponseEntity.status(200).body(groupOfPeopleService.delete(id));
  }
  @GetMapping
  public ResponseEntity<Iterable<GroupOfPeopleDto>> findAll() {
    return ResponseEntity.status(200).body(groupOfPeopleService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<GroupOfPeopleDto> findById(@PathVariable Long id) {
    return ResponseEntity.status(200).body(groupOfPeopleService.findById(id));
  }
  @GetMapping("/name/{name}")
  public ResponseEntity<Iterable<GroupOfPeopleDto>> findByName(@PathVariable String name) {
    return ResponseEntity.status(200).body(groupOfPeopleService.findByName(name));
  }
}
