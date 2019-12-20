package ru.eltex.testsystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class QuestionModel {
    private String question;
    private ArrayList<String> trueAnswer;
    private String point;
    private ArrayList<String> answers;
}
