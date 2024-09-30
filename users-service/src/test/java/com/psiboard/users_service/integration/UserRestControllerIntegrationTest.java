package com.psiboard.users_service.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.assertj.core.api.Assertions;

import com.psiboard.users_service.application.dto.UserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.domain.UserRole;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserRestControllerIntegrationTest {

    @Autowired
    WebTestClient testClient;

    @Test
    @DisplayName("Deve criar um usuário")
    public void shouldCreateUser() {
        UserResponseDto responseBody = testClient
                .post()
                .uri("/auth/register")
                .bodyValue(new UserRequestDto(
                        "Zezinho Silva",
                        "zezinho.silva@example.com",
                        "123456",
                        "+5511999999999",
                        UserRole.PROFESSIONAL
                )).exchange()
                .expectStatus().isOk().expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
    }

}
