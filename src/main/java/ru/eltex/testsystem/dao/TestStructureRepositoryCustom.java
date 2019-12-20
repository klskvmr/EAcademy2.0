package ru.eltex.testsystem.dao;

import ru.eltex.testsystem.model.TestStructure;

public interface TestStructureRepositoryCustom {
    TestStructure getByName(String name);
    TestStructure getById(String id);

}
