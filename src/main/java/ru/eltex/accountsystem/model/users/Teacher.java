package ru.eltex.accountsystem.model.users;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс представления учителя
 * @author Maria Koloskova
 * @version v2.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "teachers")
public class Teacher extends User {
    /** Поле идентификаторов дисциплин */
    private List<String> subjectIds;

    public Teacher(String login, String password, String email,
                   String fio, Role role,
                   List<String> subjectIds) {
        super(login, password, email, fio, role);

        this.subjectIds = subjectIds;
    }

    public Teacher(Map<String, String> user, List<String> subjectIds) {
        super(user.get("login"), user.get("password"), user.get("email"), user.get("fio"), Role.valueOf(user.get("role")));
        this.subjectIds = subjectIds;
    }
}
