package com.psiboard.users_service.framework.helpers;

import org.springframework.security.core.userdetails.UserDetails;

import com.psiboard.users_service.application.dto.UserResponseDto;
import com.psiboard.users_service.domain.User;

public class Utils {

    public static UserResponseDto convertToUserResponseDto(UserDetails userDetails) {
        if (userDetails instanceof User) {
            User user = (User) userDetails;
            return new UserResponseDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getContact(),
                    user.getRole());
        }
        return null; // Tratar exceção adequadamente
    }

}
