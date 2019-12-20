package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.accountsystem.dao.TeacherRepositoryCustom;
import ru.eltex.accountsystem.model.users.Teacher;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String>, TeacherRepositoryCustom {
    Teacher findByFio(String fio);
}
