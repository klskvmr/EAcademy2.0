package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.accountsystem.dao.TestResultRepositoryCustom;
import ru.eltex.accountsystem.model.TestResult;

@Repository
public interface TestResultRepository extends MongoRepository<TestResult, String>, TestResultRepositoryCustom {
}
