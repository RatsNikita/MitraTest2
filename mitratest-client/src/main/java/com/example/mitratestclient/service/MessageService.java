package com.example.mitratestclient.service;

import com.example.mitratestclient.dto.MessageDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MessageService {

  private final WebClient webClient;

  public Mono<MessageDto> getMessage(Long id) {
    return webClient
            .get()
            .uri("/get/{id}",id)
            .retrieve()
            .bodyToMono(MessageDto.class);
  }
  public Flux<MessageDto> getMessagesByDate(LocalDate from, LocalDate to) {
    return webClient
            .get()
            .uri("/get?from=" + from + "&to=" + to)
            .retrieve()
            .bodyToFlux(MessageDto.class);
  }

  public Mono<String> saveMessage(String message) {
    return webClient
            .post()
            .uri("/save")
            .body(Mono.just(message),String.class)
            .retrieve()
            .bodyToMono(String.class);
  }

}
