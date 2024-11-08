package com.psiboard.users_service.adapters.out;

import com.psiboard.users_service.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);
}