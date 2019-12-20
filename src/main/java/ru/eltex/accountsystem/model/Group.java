package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Класс представления групп
 * @author Arina Nedobitkova
 * @version v2.0
 */
@Getter
@Setter
@Document(collection = "groups")
public class Group {
    /** Поле идентификатора */
    @Id
    private String id;

    /** Поле названия */
    private String title;

    /** Поле расписания */
    private String table;

    /** Поле идентификаторов студентов */
    private ArrayList<String> studentIds;

    public Group() {
        this.id=UUID.randomUUID().toString();
    }

    public Group(String title, ArrayList<String> studentIds) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.studentIds = studentIds;
    }

    public Group(String title, String table, ArrayList<String> studentIds) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.table = table;
        this.studentIds = studentIds;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", table='" + table + '\'' +
                ", studentIds=" + studentIds +
                '}';
    }
}