package com.chien.demoPerson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DemoPersonApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoPersonApplication.class, args);
  }

}
