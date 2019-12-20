package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;

/**
 * Класс представления дисциплин
 * @author Alexey Masis
 * @version v2.0
 */
@Getter
@Setter
@Document(collection = "subjects")
public class Subject {
    /** Поле идентификатора */
    private String id;

    /** Поле названия */
    private String title;

    /** Поле идентификаторов заданий */
    private List<String> taskIds;

    /** Поле идентификаторов групп */
    private List<String> groupIds;

    /** Поле идентификаторов тестов */
    private List<String> testIds;

    public Subject() {
        this.id= UUID.randomUUID().toString();
    }

    public Subject(String title, List<String> taskIds, List<String> groupIds, List<String> testIds) {
        this.title = title;
        this.taskIds = taskIds;
        this.groupIds = groupIds;
        this.testIds = testIds;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", taskIds=" + taskIds +
                ", groupIds=" + groupIds +
                ", testIds=" + testIds +
                '}';
    }
}