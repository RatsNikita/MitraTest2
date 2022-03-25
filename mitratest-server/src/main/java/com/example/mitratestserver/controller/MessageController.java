package com.example.mitratestserver.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public interface MessageController {

  @PostMapping("/save")
  void postMessage(String message);

  @GetMapping("/get/{id}")
  ResponseEntity<?> getMessageById(@PathVariable Long id);

  @GetMapping("/get")
  ResponseEntity<?> getMessageByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to);
}
