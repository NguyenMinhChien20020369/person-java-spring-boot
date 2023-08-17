package com.chien.demoPerson.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupOfPeopleCreationDto {

  @NotEmpty(message = "Thiếu tên!")
  private String name;
  @NotEmpty(message = "Bạn chưa thêm người!")
  private List<Long> personIds;
}
