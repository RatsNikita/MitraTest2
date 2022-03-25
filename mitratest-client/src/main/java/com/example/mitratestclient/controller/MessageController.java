package com.example.mitratestclient.controller;


import com.example.mitratestclient.dto.MessageDto;
import com.example.mitratestclient.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @GetMapping("/get/{id}")
  public Mono<MessageDto> getMessageById(@PathVariable Long id) {
    return messageService.getMessage(id);
  }

  @GetMapping("/get")
  public Flux<MessageDto> getMessageByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate from,
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
    return messageService.getMessagesByDate(from,to);
  }

  @PostMapping("/save")
  public Mono<String> saveMessage(@RequestBody String message) {
    return messageService.saveMessage(message);
  }
}
