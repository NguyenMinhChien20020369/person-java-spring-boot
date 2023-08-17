package com.chien.demoPerson.repository;

import com.chien.demoPerson.entity.GroupOfPeople;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupOfPeopleRepository extends JpaRepository<GroupOfPeople, Long> {

  List<GroupOfPeople> findByName(String name);
}
