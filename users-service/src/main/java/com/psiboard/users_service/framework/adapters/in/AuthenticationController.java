package com.psiboard.users_service.framework.adapters.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psiboard.users_service.application.dto.LoginRequestDto;
import com.psiboard.users_service.application.dto.LoginResponseDto;
import com.psiboard.users_service.application.dto.UserRequestDto;
import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.application.ports.in.TokenServiceInputPort;
import com.psiboard.users_service.application.ports.in.UserServiceInputPort;
import com.psiboard.users_service.domain.User;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserServiceInputPort serviceInputPort;
    private final TokenServiceInputPort tokenServiceInputPort;

    public AuthenticationController(AuthenticationManager authenticationManager, UserServiceInputPort serviceInputPort,
            TokenServiceInputPort tokenServiceInputPort) {
        this.authenticationManager = authenticationManager;
        this.serviceInputPort = serviceInputPort;
        this.tokenServiceInputPort = tokenServiceInputPort;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenServiceInputPort.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto user) {
        if (serviceInputPort.getUserRepository().findByEmail(user.getEmail()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        UserRequestDto newUser = new UserRequestDto(user.getName(), user.getEmail(), encryptedPassword,
                user.getContact(), user.getRole());

        UserResponseDto savedUser = serviceInputPort.create(newUser);

        return ResponseEntity.ok().body(savedUser);

    }
}
