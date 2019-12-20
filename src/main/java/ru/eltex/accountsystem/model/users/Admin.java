package ru.eltex.accountsystem.model.users;

import org.springframework.data.mongodb.core.mapping.Document;

import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;

import java.util.Map;


@Document(collection = "admins")
public class Admin extends User {
    public Admin(String login, String password, String email,
                 String fio, Role role) {
        super(login, password, email, fio, role);
    }

    public Admin(Map<String, String> user) {
        super(user.get("login"), user.get("password"), user.get("email"), user.get("fio"), Role.valueOf(user.get("role")));
    }
}
