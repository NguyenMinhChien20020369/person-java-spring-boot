package com.chien.demoPerson.service.impl;

import com.chien.demoPerson.aspect.TrackTime;
import com.chien.demoPerson.dto.PersonCreationDto;
import com.chien.demoPerson.dto.PersonDto;
import com.chien.demoPerson.dto.PersonUpdateDto;
import com.chien.demoPerson.entity.Person;
import com.chien.demoPerson.exception.AppException;
import com.chien.demoPerson.repository.PersonRepository;
import com.chien.demoPerson.service.PersonService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private ModelMapper mapper;
  @PersistenceContext
  public EntityManager em;

  @Override
  public PersonDto create(PersonCreationDto personCreationDto) {
    return mapper.map(personRepository.save(mapper.map(personCreationDto, Person.class)),
        PersonDto.class);
  }

  @Override
  public PersonDto update(Long id, PersonUpdateDto personUpdateDto) {
    Person fromDB = personRepository.findById(id).orElse(null);
    if (fromDB == null) {
      throw new AppException(404, "User not found");
    }
    fromDB.setEmail(personUpdateDto.getEmail());
    fromDB.setName(personUpdateDto.getName());
    fromDB.setPhone(personUpdateDto.getPhone());
    fromDB.setAddress(personUpdateDto.getAddress());
    return mapper.map(personRepository.save(fromDB), PersonDto.class);
  }

  @Override
  public PersonDto delete(Long id) {
    Person fromDB = personRepository.findById(id).orElse(null);
    if (fromDB == null) {
      throw new AppException(404, "User not found");
    }
    personRepository.deleteById(id);
    return mapper.map(fromDB, PersonDto.class);
  }

  @TrackTime
  @Override
  public PersonDto findById(Long id) {
    Person person = personRepository.findById(id).orElse(null);
    if (person == null) {
      throw new AppException(404, "User not found");
    } else {
      return mapper.map(person, PersonDto.class);
    }
  }

  @Override
  public List<PersonDto> findByName(String name) {
    return personRepository.findByName(name).stream()
        .map(person -> mapper.map(person, PersonDto.class)).collect(
            Collectors.toList());
  }

  @Override
  public List<PersonDto> findByPhone(String phone) {
//    return em.createNamedQuery("Person.findByPhone").setParameter("phone", phone).getResultList();
    return personRepository.findByPhone(phone).stream()
        .map(person -> mapper.map(person, PersonDto.class)).collect(
            Collectors.toList());
  }

  @TrackTime
  @Override
  public Iterable<PersonDto> findAll() {
    List<Person> Persons = personRepository.findAll();
    List<PersonDto> personDtos = new ArrayList<PersonDto>();
    for (Person person : Persons) {
      System.out.println(person);
      PersonDto personDto = mapper.map(person, PersonDto.class);
      personDtos.add(personDto);
    }
    return personDtos;
  }
}
