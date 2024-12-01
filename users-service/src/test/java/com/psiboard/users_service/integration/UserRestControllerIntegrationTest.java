package com.psiboard.users_service.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.springframework.core.ParameterizedTypeReference;

import com.psiboard.users_service.core.application.dto.LoginRequestDto;
import com.psiboard.users_service.core.application.dto.PatientResponseDto;
import com.psiboard.users_service.core.application.dto.UpdateUserRequestDto;
import com.psiboard.users_service.core.application.dto.UserRequestDto;
import com.psiboard.users_service.core.application.dto.UserResponseDto;
import com.psiboard.users_service.core.domain.UserRole;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRestControllerIntegrationTest {

    @Autowired
    WebTestClient testClient;

    private String jwtToken;

    @BeforeEach
    public void setupAll() {
        UserRequestDto userRequest = new UserRequestDto(
            "Admin Silva",
            "admin.silva@example.com",
            "123456",
            "+5511999999999",
            UserRole.ADMIN);
        testClient
        .post()
        .uri("/auth/register")
        .bodyValue(userRequest)
        .exchange()
        .expectStatus().isOk().expectBody(UserResponseDto.class)
        .returnResult().getResponseBody();

    }

    @BeforeEach
    public void setup() {
        // Autenticar e obter o token JWT
        LoginRequestDto loginRequest = new LoginRequestDto(
                "admin.silva@example.com",
                "12345");

        jwtToken = testClient
                .post()
                .uri("/auth/login")
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();
    }

    @Test
    @DisplayName("Deve criar um usuário com perfil Profissional")
    public void shouldCreateProfessionalUser() {
        UserResponseDto responseBody = testClient
                .post()
                .uri("/auth/register")
                .bodyValue(new UserRequestDto(
                        "Zezinho Silva",
                        "zezinho.silva@example.com",
                        "123456",
                        "+5511999999999",
                        UserRole.PROFESSIONAL))
                .exchange()
                .expectStatus().isOk().expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getEmail()).isEqualTo("zezinho.silva@example.com");
    }

    @Test
    @DisplayName("Deve criar um usuário com perfil Admin")
    public void shouldCreateAdminUser() {
        UserResponseDto responseBody = testClient
                .post()
                .uri("/auth/register")
                .bodyValue(new UserRequestDto(
                        "Ademiro Silva",
                        "ademiro.silva@example.com",
                        "123456",
                        "+5511999999999",
                        UserRole.ADMIN))
                .exchange()
                .expectStatus().isOk().expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getEmail()).isEqualTo("zezinho.silva@example.com");
    }

    @Test
    @DisplayName("Deve retornar todos os usuários")
    public void shouldReturnAllUsers() {
        List<UserResponseDto> responseBody = testClient
                .get()
                .uri("/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotEmpty();
    }

    @Test
    @DisplayName("Deve retornar um usuário por ID")
    public void shouldReturnUserById() {
        String userId = "100";

        UserResponseDto responseBody = testClient
                .get()
                .uri("/users/{id}", userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Deve retornar pacientes para um usuário")
    public void shouldReturnPatientsForUser() {
        String userId = "100";

        List<PatientResponseDto> responseBody = testClient
                .get()
                .uri("/users/{userId}/patients", userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PatientResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotEmpty();
    }

    @Test
    @DisplayName("Deve retornar um usuário por email")
    public void shouldReturnUserByEmail() {
        String email = "zezinho.silva@example.com";

        UserResponseDto responseBody = testClient
                .get()
                .uri("/users/user/{email}", email)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("Deve atualizar um usuário")
    public void shouldUpdateUser() {
        String userId = "100"; 

        UpdateUserRequestDto updateUser = new UpdateUserRequestDto(
                "Zezinho Silva Atualizado",
                "zezinho.silva.atualizado@example.com",
                "+5511999999998");

        UserResponseDto responseBody = testClient
                .put()
                .uri("/users/{id}", userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .bodyValue(updateUser)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getName()).isEqualTo("Zezinho Silva Atualizado");
        Assertions.assertThat(responseBody.getEmail()).isEqualTo("zezinho.silva.atualizado@example.com");
    }

    @Test
    @DisplayName("Deve deletar um usuário")
    public void shouldDeleteUser() {
        String userId = "100";

        testClient
                .delete()
                .uri("/users/{id}", userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @DisplayName("Deve retornar fallback quando o serviço de pacientes estiver indisponível")
    public void shouldReturnFallbackWhenPatientServiceIsUnavailable() {
        String userId = "100";

        Map<String, Object> responseBody = testClient
                .get()
                .uri("/users/{userId}/patients", userId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.get("message"))
                .isEqualTo("O serviço de pacientes está temporariamente indisponível");
        Assertions.assertThat(responseBody.get("data")).isEqualTo(Collections.emptyList());
    }

}
