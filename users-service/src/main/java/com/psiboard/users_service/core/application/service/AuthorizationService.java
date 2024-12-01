package com.psiboard.users_service.core.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.psiboard.users_service.core.application.ports.out.UserPersistencePort;
import com.psiboard.users_service.adapters.out.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserPersistencePort userPersistencePort;

    public AuthorizationService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRepository userRepository = userPersistencePort.getUserRepository();
        UserDetails user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return user;

    }

}
