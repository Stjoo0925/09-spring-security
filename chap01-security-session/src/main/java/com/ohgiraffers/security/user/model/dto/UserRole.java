package com.ohgiraffers.security.user.model.dto;

public enum UserRole {

    USER("USER"),

    ADMIN("ADMIN"),

    // 다중권한 주기
    // USER, ADMIN 두개의 권한을 줄 수 있다.
    ALL("USER, ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }
}
