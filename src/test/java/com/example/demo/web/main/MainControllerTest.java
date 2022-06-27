package com.example.demo.web.main;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor
public class MainControllerTest {

    private final TestRestTemplate testRestTemplate;

    @Test
    public void loadMainPage() {

        String body = testRestTemplate.getForObject("/main", String.class);

        assertThat(body).contains("<!DOCTYPE html>");

    }

}
