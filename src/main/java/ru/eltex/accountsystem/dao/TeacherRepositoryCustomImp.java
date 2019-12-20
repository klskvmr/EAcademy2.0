package ru.eltex.accountsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.eltex.accountsystem.model.users.Teacher;

public class TeacherRepositoryCustomImp implements TeacherRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Teacher findTeacherById(String id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("id").regex(id)), Teacher.class);
    }


}
