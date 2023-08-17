package com.chien.demoPerson.service;

import com.chien.demoPerson.dto.PersonCreationDto;
import com.chien.demoPerson.dto.PersonDto;
import com.chien.demoPerson.dto.PersonUpdateDto;
import java.util.List;

public interface PersonService extends
    GeneralService<PersonDto, PersonCreationDto, PersonUpdateDto> {

  List<PersonDto> findByPhone(String phone);
}
