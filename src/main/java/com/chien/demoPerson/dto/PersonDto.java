package com.chien.demoPerson.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDto {

  private Long id;

  private String username;

  private String name;

  private String email;

  private String phone;

  private String address;
}
