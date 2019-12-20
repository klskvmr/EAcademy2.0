package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Класс представления заданий
 * @author Arina Nedobitkova
 * @version v2.0
 */
@Getter
@Setter
@Document(collection = "taskTeacher")
public class Task {

    /** Поле идентификатора */
    @Id
    private String id;

    /** Поле названия задания */
    private String title;

    /** Поле описания задания */
    private String description;

    /** Поле максимальных баллов за задание */
    private Integer maxScores;

    public Task() {
        id = UUID.randomUUID().toString();
    }

    public Task(String title, String description, Integer maxScores) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.maxScores = maxScores;
    }
}