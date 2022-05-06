package utils;

import com.example.mitratestserver.entity.MessageEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestUtils {

  public static MessageEntity getDefaultMessage() {
    MessageEntity message = new MessageEntity();
    message.setId(1L);
    message.setBody("testMessage");
    message.setDate(LocalDate.now());
    message.setTime(LocalTime.now());
    return message;
  }

}
