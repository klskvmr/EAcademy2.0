package ru.eltex.accountsystem.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Класс представления расписания
 * @author Alexey Masis
 * @version v2.0
 */
@Getter
@Setter
@Document(collection = "table")
public class Table {
    /** Поле идентификатора */
    @Id
    private String id;

    /** Поле времени */
    private String time;

    /** Поле дней недели */
    private ArrayList<ArrayList<String>> dayWeek;

    public Table() {
        this.id = UUID.randomUUID().toString();
    }
}

