package ru.eltex.accountsystem.dao;

import ru.eltex.accountsystem.model.TestResult;

public interface TestResultRepositoryCustom  {
    TestResult getByIdAndIdTest(String id, String testId);
}
