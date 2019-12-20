package ru.eltex.testsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Getter@Setter
@Document(collection = "teststructure")
public class TestStructure {

    private String id;
    private String name;
    private ArrayList<Integer> graduation;
    private String type;

    @Override
    public String toString() {
        return "TestStructure{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", graduation=" + graduation +
                ", type='" + type + '\'' +
                ", repassing='" + repassing + '\'' +
                ", someAnswers='" + someAnswers + '\'' +
                ", timelimite='" + timelimite + '\'' +
                ", test=" + test +
                '}';
    }

    private String repassing;
    private String someAnswers;
    private String timelimite;
    private ArrayList<QuestionModel> test;

    public TestStructure() {
        this.id = UUID.randomUUID().toString();
    }
}
