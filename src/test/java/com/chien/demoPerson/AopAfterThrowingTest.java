package com.chien.demoPerson;

import com.chien.demoPerson.exception.AppException;
import com.chien.demoPerson.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopAfterThrowingTest {

  @Autowired
  private PersonService personService;

  @Test(expected = AppException.class)
  public void testPersonFindById() {
    personService.findById(1L);
  }
}
