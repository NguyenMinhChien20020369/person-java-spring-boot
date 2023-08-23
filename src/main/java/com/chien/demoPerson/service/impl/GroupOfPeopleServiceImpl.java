package com.chien.demoPerson.service.impl;

import com.chien.demoPerson.config.ModelMapperConfig;
import com.chien.demoPerson.dto.GroupOfPeopleCreationDto;
import com.chien.demoPerson.dto.GroupOfPeopleDto;
import com.chien.demoPerson.dto.PersonDto;
import com.chien.demoPerson.entity.GroupOfPeople;
import com.chien.demoPerson.entity.Person;
import com.chien.demoPerson.exception.AppException;
import com.chien.demoPerson.repository.GroupOfPeopleRepository;
import com.chien.demoPerson.repository.PersonRepository;
import com.chien.demoPerson.service.GroupOfPeopleService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupOfPeopleServiceImpl implements GroupOfPeopleService {

  @Autowired
  private GroupOfPeopleRepository groupOfPeopleRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ModelMapper mapper;

  @Override
  public GroupOfPeopleDto create(GroupOfPeopleCreationDto groupOfPeopleCreationDto) {
    GroupOfPeople groupOfPeople = mapper.map(groupOfPeopleCreationDto, GroupOfPeople.class);
    Set<Person> persons = new HashSet<>();
    for (Long id : groupOfPeopleCreationDto.getPersonIds()) {
      Person person = personRepository.findById(id).orElse(null);
      if (person == null) {
        throw new AppException(404, "Person not found");
      }
      persons.add(person);
    }
    groupOfPeople.setPersons(persons);
    return mapper.map(groupOfPeopleRepository.save(groupOfPeople), GroupOfPeopleDto.class);
  }

  @Override
  public GroupOfPeopleDto update(Long id, GroupOfPeopleCreationDto groupOfPeopleCreationDto) {
    GroupOfPeople fromDB = groupOfPeopleRepository.findById(id).orElse(null);
    if (fromDB == null) {
      throw new AppException(404, "Group not found");
    }
    fromDB.setName(groupOfPeopleCreationDto.getName());
    Set<Person> persons = new HashSet<>();
    for (Long personId : groupOfPeopleCreationDto.getPersonIds()) {
      Person person = personRepository.findById(personId).orElse(null);
      if (person == null) {
        throw new AppException(404, "Person not found");
      }
      persons.add(person);
    }
    fromDB.setPersons(persons);
    return mapper.map(groupOfPeopleRepository.save(fromDB), GroupOfPeopleDto.class);
  }

  @Override
  public GroupOfPeopleDto delete(Long id) {
    GroupOfPeople fromDB = groupOfPeopleRepository.findById(id).orElse(null);
    if (fromDB == null) {
      throw new AppException(404, "Group not found");
    }
    GroupOfPeopleDto groupOfPeopleDto = mapper.map(fromDB, GroupOfPeopleDto.class);
    groupOfPeopleRepository.deleteById(id);
    return groupOfPeopleDto;
  }

  @Override
  public GroupOfPeopleDto findById(Long id) {
    GroupOfPeople groupOfPeople = groupOfPeopleRepository.findById(id).orElse(null);
    if (groupOfPeople == null) {
      throw new AppException(404, "Group not found");
    } else {
      return mapper.map(groupOfPeople, GroupOfPeopleDto.class);
    }
  }

  @Override
  public List<GroupOfPeopleDto> findByName(String name) {
    return groupOfPeopleRepository.findByName(name).stream()
        .map(groupOfPeople -> mapper.map(groupOfPeople, GroupOfPeopleDto.class)).collect(
            Collectors.toList());
  }

  @Override
  public Iterable<GroupOfPeopleDto> findAll() {
    return groupOfPeopleRepository.findAll().stream().map(groupOfPeople -> mapper.map(groupOfPeople, GroupOfPeopleDto.class)).collect(
        Collectors.toList());
  }
}
