package com.example.mitratestserver.service;

import com.example.mitratestserver.entity.MessageEntity;
import com.example.mitratestserver.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepository repository;

public void save(String body) {
  MessageEntity message = new MessageEntity();
  message.setBody(body);
  message.setDate(LocalDate.now());
  message.setTime(LocalTime.now());
  repository.save(message);
}

public List<MessageEntity> getByDate(LocalDate from, LocalDate to) {
  return repository.findAllByDateBetween(from, to);
}

public Optional<MessageEntity> getById(Long id) {
  return repository.findById(id);
}
}
