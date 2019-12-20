package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.TaskResult;

import java.util.List;

public interface TaskResultRepository extends MongoRepository<TaskResult, String> {
    List<TaskResult> findByIdTeacherTask(String idTask);
    TaskResult findByIdStudent(String idStudent);
}