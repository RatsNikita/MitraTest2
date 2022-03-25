package com.example.mitratestserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
public class MessageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String body;
  private LocalDate date;
  private LocalTime time;
}
