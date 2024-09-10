package com.psiboard.users_service.domain;

public enum UserRole {
    ADMIN("admin"),
    PROFESSIONAL("professional");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
