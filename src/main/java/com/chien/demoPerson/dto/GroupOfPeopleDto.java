package com.chien.demoPerson.dto;

import com.chien.demoPerson.entity.Goods;
import java.util.Collection;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupOfPeopleDto {

  private Long id;
  private String name;
  private Set<PersonDto> persons;
  private Collection<Goods> goods;
}
