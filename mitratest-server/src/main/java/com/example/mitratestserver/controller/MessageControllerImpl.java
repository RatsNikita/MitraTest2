package com.example.mitratestserver.controller;

import com.example.mitratestserver.entity.MessageEntity;
import com.example.mitratestserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController{

  private final MessageService messageService;

  @Override
  public void postMessage(@RequestBody String message) {
    messageService.save(message);
  }

  @Override
  public ResponseEntity<?> getMessageById(Long id) {
    Optional<MessageEntity> response = messageService.getById(id);
    return response.isPresent()
            ? new ResponseEntity<>(response,HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<?> getMessageByDate(LocalDate from, LocalDate to) {
    List<MessageEntity> response = messageService.getByDate(from,to);
    return response.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(response, HttpStatus.OK);
  }
}
