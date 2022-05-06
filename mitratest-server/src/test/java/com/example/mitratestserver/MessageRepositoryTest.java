package com.example.mitratestserver;


import com.example.mitratestserver.entity.MessageEntity;
import com.example.mitratestserver.repository.MessageRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.getDefaultMessage;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageRepositoryTest {

  @Container
  static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12");

  @DynamicPropertySource
  static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
    propertyRegistry.add("spring.datasource.url", container::getJdbcUrl);
    propertyRegistry.add("spring.datasource.password", container::getPassword);
    propertyRegistry.add("spring.datasource.username", container::getUsername);
  }


  @Autowired
  private  MessageRepository messageRepository;

  @Test
  public void containerCanStart() {
    container.isRunning();
  }

  @Test
  public void shouldReturnEmptyIfDBIsClean() {
    //when
    List<MessageEntity> messages = messageRepository.findAllByDateBetween(LocalDate.parse("2020-01-01"),
            LocalDate.parse("2022-01-01"));
    //then
    assertEquals(0,messages.size());
  }

  @Test
  public void shouldReturnNotEmptyListIfDBContainsRecords() {
    //given
    MessageEntity message = getDefaultMessage();
    messageRepository.save(message);
    //when
    List<MessageEntity> messages = messageRepository.findAll();
    //then
    assertNotEquals(0,messages.size());
    assertEquals(message,messages.get(0));
  }



}
