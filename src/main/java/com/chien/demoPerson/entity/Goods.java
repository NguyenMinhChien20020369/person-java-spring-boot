package com.chien.demoPerson.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String price;
  private int amount;
  private Date date;
  private boolean payAll;
  private boolean paid;

  @ManyToOne
  @JoinColumn(name = "person_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Person person;

  @ManyToOne
  @JoinColumn(name = "group_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private GroupOfPeople group;
}
