package com.example.mitratestserver;


import com.example.mitratestserver.entity.MessageEntity;
import com.example.mitratestserver.repository.MessageRepository;
import com.example.mitratestserver.service.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static utils.TestUtils.getDefaultMessage;

@SpringBootTest
public class MessageServiceTest {

  @MockBean
  private MessageRepository messageRepository;

  @InjectMocks
  @Autowired
  private MessageService messageService;

  @Test
  public void canSaveMessages() {
    //given
    String testBody = "testBody";
    //when
    messageService.save(testBody);
    //then
    verify(messageRepository,times(1)).save(any());
  }

  @Test
  public void canGetByDate() {
    //given
    MessageEntity message = getDefaultMessage();
    when(messageRepository.findAllByDateBetween(any(),any()))
            .thenReturn(List.of(message));
    //when
    List<MessageEntity> expectedList =
            messageService.getByDate(LocalDate.parse("2022-01-22"),LocalDate.parse("2022-04-22"));
    //then
    assertTrue(expectedList.contains(message));
  }

  @Test
  public void canGetById() {
    //given
    MessageEntity message = getDefaultMessage();
    when(messageRepository.findById(1L))
            .thenReturn(Optional.of(message));
    //when
    Optional<MessageEntity> expectedMessage =
            messageService.getById(1L);
    //then
    assertEquals(message, expectedMessage.get());
  }
}
