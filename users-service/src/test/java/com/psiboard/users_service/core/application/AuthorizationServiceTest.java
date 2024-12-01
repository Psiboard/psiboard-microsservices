package com.psiboard.users_service.core.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.psiboard.users_service.adapters.out.UserRepository;
import com.psiboard.users_service.core.application.ports.out.UserPersistencePort;
import com.psiboard.users_service.core.application.service.AuthorizationService;




public class AuthorizationServiceTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(userPersistencePort.getUserRepository()).thenReturn(userRepository);
    }

    @Test
    public void testLoadUserByUsername_Success() {
        String username = "test@example.com";
        UserDetails userDetails = mock(UserDetails.class);
        when(userRepository.findByEmail(username)).thenReturn(userDetails);

        UserDetails result = authorizationService.loadUserByUsername(username);

        assertEquals(userDetails, result);
        verify(userRepository).findByEmail(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "notfound@example.com";
        when(userRepository.findByEmail(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            authorizationService.loadUserByUsername(username);
        });

        verify(userRepository).findByEmail(username);
    }
}
