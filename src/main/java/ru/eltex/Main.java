package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.accountsystem.repository.TaskRepository;
import ru.eltex.accountsystem.repository.TeacherRepository;
import java.util.ArrayList;

@SpringBootApplication
public class Main {
    public static void main(java.lang.String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository, SubjectRepository subjectRepository,
                                  TeacherRepository teacherRepository, TaskRepository taskRepository) {
        return (args) -> {
//            ArrayList<String> subjectIds = new ArrayList<>();
//            subjectIds.add("5d6629535156767178379b26");
//            subjectIds.add("5d66376b5156767be1c92f77");
//
//            Teacher teacher = new Teacher("te4er", "password", "tt@gmail.com",
//                    "Ivanov I.I.", Role.TEACHER, subjectIds);
//            teacherRepository.save(teacher);
        };
    }
}
