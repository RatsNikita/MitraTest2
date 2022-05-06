package com.example.mitratestserver.repository;

import com.example.mitratestserver.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long> {

   List<MessageEntity> findAllByDateBetween(LocalDate from, LocalDate to);

}
