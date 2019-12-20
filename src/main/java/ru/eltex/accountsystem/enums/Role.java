package ru.eltex.accountsystem.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT,
    TEACHER,
    GRADUATE,
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
