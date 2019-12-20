package ru.eltex.testsystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestAnswers {

    private List<List<String>> checkedItems;


    @Override
    public String toString() {
        return "TestAnswers{" +
                "checkedItems=" + checkedItems +
                '}';
    }

}

