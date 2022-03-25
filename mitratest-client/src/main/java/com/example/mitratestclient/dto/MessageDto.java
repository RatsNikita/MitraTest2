package com.example.mitratestclient.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MessageDto {

  private Long id;
  private String body;
  private LocalDate date;
  private LocalTime time;
}
