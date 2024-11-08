package com.psiboard.users_service.core.application.ports.in;

import java.time.Instant;
import com.psiboard.users_service.core.domain.User;

public interface TokenServiceInputPort {
    String generateToken(User user);

    String validateToken(String token);

    Instant genExpirationDate();
}
