package ru.eltex.accountsystem.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.model.users.Admin;
import ru.eltex.accountsystem.model.users.Graduate;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserRegistrationService {
    private final AllUserRepository allUserRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final GraduateRepository graduateRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserRegistrationService(AllUserRepository allUserRepository, StudentRepository studentRepository,
                                   TeacherRepository teacherRepository, AdminRepository adminRepository,
                                   GraduateRepository graduateRepository, ObjectMapper objectMapper) {
        this.allUserRepository = allUserRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
        this.graduateRepository = graduateRepository;
        this.objectMapper = objectMapper;
    }

    public String register(JsonNode jsonNode) {
        Map<String, String> userMap = objectMapper.convertValue(jsonNode, Map.class);
        String userId="";
        if (allUserRepository.findByUsername(userMap.get("login")) != null) {
            return "Ошибка, пользователь с таким логином и паролем уже существует.";
        } else {
            switch (Role.valueOf(userMap.get("role"))) {
                case GRADUATE: {
                    Graduate graduate = new Graduate(userMap, null, null);
                    userId=graduate.getId();
                    graduateRepository.save(graduate);
                    break;
                }
                case TEACHER: {
                    Teacher teacher = new Teacher(userMap, new ArrayList<>());
                    userId=teacher.getId();
                    teacherRepository.save(teacher);
                    break;
                }
                case STUDENT: {
                    Student student = new Student(userMap, new ArrayList<>(), "");
                    userId=student.getId();
                    studentRepository.save(student);
                    break;
                }
                case ADMIN: {
                    Admin admin = new Admin(userMap);
                    userId=admin.getId();
                    adminRepository.save(admin);
                }
            }

            List<Role> role = new ArrayList<>();
            role.add(Role.valueOf(userMap.get("role")));

            UserRole userRole = new UserRole(role, userMap, userId);
            allUserRepository.save(userRole);

            return "Регистрация прошла успешно.";
        }
    }
}