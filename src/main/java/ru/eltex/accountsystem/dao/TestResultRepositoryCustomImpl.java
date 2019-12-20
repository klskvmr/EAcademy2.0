package ru.eltex.accountsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.TestResult;

@Service
public class TestResultRepositoryCustomImpl implements TestResultRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public TestResult getByIdAndIdTest(String id, String testId) {
        return mongoTemplate.findOne(Query.query(Criteria.where("studentId").regex(id).and("testId").regex(testId)), TestResult.class);
    }
}
