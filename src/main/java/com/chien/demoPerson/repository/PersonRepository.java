package com.chien.demoPerson.repository;

import com.chien.demoPerson.entity.Person;
import jakarta.persistence.NamedQuery;
import java.util.List;
import jdk.jfr.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  @Query(value = "Select * from person where name = :name", nativeQuery = true)
  List<Person> findByName(@Param("name") String name);
  List<Person> findByPhone(String phone);
}
