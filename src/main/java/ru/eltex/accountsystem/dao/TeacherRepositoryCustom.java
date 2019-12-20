package ru.eltex.accountsystem.dao;

import ru.eltex.accountsystem.model.users.Teacher;

public interface TeacherRepositoryCustom {
    Teacher findTeacherById(String id);
}
