package ru.eltex.accountsystem.model.users;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Sex;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Класс представления выпускника
 * @author Maria Koloskova
 * @version v2.0
 */
@Getter
@Setter
@Document(collection = "graduates")
public class Graduate extends User {
    /** Поле возраста */
    Integer age;

    /** Поле пола */
    Sex sex;

    public Graduate(String login, String password,
                    String email, String fio, Role role, Integer age, Sex sex) {
        super(login, password, email, fio, role);

        this.age = age;
        this.sex = sex;
    }

    public Graduate(Map<String, String> user, Integer age, Sex sex) {
        super(user.get("login"), user.get("password"), user.get("email"), user.get("fio"), Role.valueOf(user.get("role")));
        this.age = age;
        this.sex = sex;
    }
}
