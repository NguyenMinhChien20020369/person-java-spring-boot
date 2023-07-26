package com.chien.demoPerson.service.impl;

import com.chien.demoPerson.dto.PersonDto;
import com.chien.demoPerson.entity.Person;
import com.chien.demoPerson.exception.AppException;
import com.chien.demoPerson.repository.PersonRepository;
import com.chien.demoPerson.service.PersonService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
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
  public Person create(PersonDto personDto) {
    return personRepository.save(mapper.map(personDto, Person.class));
  }

  @Override
  public Person update(Long id, Person person) {
    Person fromDB = personRepository.findById(id).orElse(null);
    if (fromDB == null) {
      return null;
    }
    fromDB.setEmail(person.getEmail());
    fromDB.setName(person.getName());
    fromDB.setPhone(person.getPhone());
    fromDB.setAddress(person.getAddress());
    return personRepository.save(fromDB);
  }

  @Override
  public void delete(Long id) {
    personRepository.deleteById(id);
  }

  @Override
  public Person findById(Long id) {
    Person person = personRepository.findById(id).orElse(null);
    if (person == null) {
      throw new AppException(404, "User not found");
    }
    return person;
  }

  @Override
  public List<Person> findByName(String name) {
    return personRepository.findByName(name);
  }

  @Override
  public List<Person> findByPhone(String phone) {
//    return em.createNamedQuery("Person.findByPhone").setParameter("phone", phone).getResultList();
    return personRepository.findByPhone(phone);
  }

  @Override
  public Iterable<PersonDto> findAll() {
    List<Person> Persons = personRepository.findAll();
    List<PersonDto> PersonDtos = new ArrayList<PersonDto>();
    for (Person person : Persons) {
      System.out.println(person);
      PersonDto personDto = mapper.map(person, PersonDto.class);
      PersonDtos.add(personDto);
    }
    return PersonDtos;
  }
}
