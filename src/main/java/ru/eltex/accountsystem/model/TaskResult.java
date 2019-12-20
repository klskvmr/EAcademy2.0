package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.TaskStatus;

/**
 * Класс представления ответов на задания
 * @author Arina Nedobitkova
 * @version v2.0
 */
@Getter
@Setter
@Document(collection = "taskResult")
public class TaskResult{
    /** Поле идентификатора */
    @Id
    private String id;

    /** Поле идентификатора задания*/
    private String idTeacherTask;

    /** Поле идентификатора студента*/
    private String idStudent;

    /** Поле ответа */
    private String answer;

    /** Поле набранных баллов */
    private Integer scores;

    /** Поле текущей оценки */
    private TaskStatus taskStatus;

    public TaskResult(String idTeacherTask, String idStudent, String answer, Integer scores) {
        this.idTeacherTask = idTeacherTask;
        this.idStudent = idStudent;
        this.answer = answer;
        this.scores = scores;
        this.taskStatus = TaskStatus.UNCHECKED;
    }
}