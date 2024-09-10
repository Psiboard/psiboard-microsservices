package com.psiboard.users_service.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.psiboard.users_service.application.ports.out.UserPersistencePort;
import com.psiboard.users_service.framework.adapters.out.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserPersistencePort userPersistencePort;

    public AuthorizationService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRepository userRepository = userPersistencePort.getUserRepository();
        return userRepository.findByEmail(username);

    }

}
