package com.github.repository.researcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GithubRepositoryResearcherApplication extends SpringBootServletInitializer {

  @Autowired private ObjectMapper objectMapper;

  public static void main(String[] args) {
    SpringApplication.run(GithubRepositoryResearcherApplication.class, args);
  }

  @PostConstruct
  public void setUp() {
    objectMapper.registerModule(new JavaTimeModule());
  }
}
