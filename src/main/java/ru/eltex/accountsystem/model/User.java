package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eltex.accountsystem.enums.Role;

import java.util.UUID;

/**
 * Класс представления пользователя
 * @author Maria Koloskova
 * @version v2.0
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class User {
    /** Поле идентификатора */
    private String id;

    /** Поле логина */
    private String login;

    /** Поле пароля */
    private String password;

    /** Поле эл. почты  */
    private String email;

    /** Поле фио */
    private String fio;

    /** Поле роли */
    private Role role;

    public User(String login, String password, String email, String fio, Role role) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.email = email;
        this.fio = fio;
        this.role = role;
    }
}