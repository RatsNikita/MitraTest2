package com.example.mitratestclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class MitratestClientApplicationTests {

  WebTestClient client =
          WebTestClient.bindToServer().baseUrl("http://localhost:8080").build()


  @Test
  void contextLoads() {
  }



}
