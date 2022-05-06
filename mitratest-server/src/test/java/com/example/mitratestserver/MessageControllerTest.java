package com.example.mitratestserver;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.mitratestserver.controller.MessageController;
import com.example.mitratestserver.entity.MessageEntity;
import com.example.mitratestserver.service.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static utils.TestUtils.getDefaultMessage;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MessageService service;

  @Test
  public void canGetMessageById() throws Exception {
    MessageEntity testMessage = getDefaultMessage();
    when(service.getById(1L)).thenReturn(Optional.of(testMessage));
    this.mockMvc.perform(get("/get/1")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.body").value("testMessage"))
            .andExpect(content().string(containsString(testMessage.getBody())));
  }
  @Test
  public void shouldRespondedNotFoundIfMessageCanNotFoundById() throws Exception{
    long id = 25L;
    this.mockMvc.perform(get("/get/"+id)).andDo(print())
            .andExpect(status().isNotFound());
    verify(service,times(1)).getById(id);
  }

  @Test
  public void canSaveMessage() throws Exception {
    String message = "testMessage";
    this.mockMvc.perform(post("/save")
            .content(message)).andDo(print())
            .andExpect(status().isOk());
    verify(service,times(1)).save(message);
  }

  @Test
  public void canGetMessageByDate() throws Exception {
    MessageEntity testMessage = getDefaultMessage();
    String from = "2021-01-01";
    String to = "2023-01-01";
    when(service.getByDate(LocalDate.parse(from),LocalDate.parse(to)))
            .thenReturn(List.of(testMessage));

    this.mockMvc.perform(get("/get")
            .param("from",from)
            .param("to",to))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].body").value("testMessage"))
            .andExpect(content().string(containsString("testMessage")));
    verify(service,times(1)).getByDate(any(),any());
  }

  @Test
  public void shouldRespondedNotFoundIfMessageCanNotFoundByDate() throws Exception {
    String from = "2020-01-01";
    String to = "2021-01-01";
    when(service.getByDate(LocalDate.parse(from),LocalDate.parse(to)))
            .thenReturn(Collections.emptyList());

    this.mockMvc.perform(get("/get")
                    .param("from",from)
                    .param("to",to))
            .andExpect(status().isNotFound());
    verify(service,times(1)).getByDate(any(),any());
  }
}
