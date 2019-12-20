package ru.eltex.accountsystem.model.users;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import java.util.ArrayList;
import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "students")
public class Student extends ru.eltex.accountsystem.model.User {
    ArrayList<String> subjectIds;
    String groupId;

    public Student(String login, String password,
                   String email, String fio, Role role,
                   ArrayList<String> subjectIds) {
        super(login, password, email, fio, role);
        this.subjectIds = subjectIds;
    }

    public Student(Map<String, String> user, ArrayList<String> subjectIds, String groupId) {
        super(user.get("login"), user.get("password"), user.get("email"), user.get("fio"), Role.valueOf(user.get("role")));
        this.subjectIds = subjectIds;
        this.groupId = groupId;
    }
}