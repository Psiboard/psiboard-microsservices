package com.psiboard.users_service.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.psiboard.users_service.application.dto.UserResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRestControllerIntegrationTest {

    @Autowired
    WebTestClient testClient;

    @Test
    @DisplayName("Deve criar um usuário")
    public void shouldCreateUser() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId("123456789");
        userResponseDto.setName("John Doe");
        userResponseDto.setEmail("john.doe@example.com");
        userResponseDto.setPassword("password123");
        userResponseDto.setContact("+55 11 99999-9999");
        UserResponseDto response = testClient
        .post()
        .uri("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(userResponseDto)
        .exchange()
        .expectStatus().isOk()
        .expectBody(UserResponseDto.class)
        .returnResult().getResponseBody();

        Assertions.assertThat(response).isNotNull();
    }

}
