package ru.eltex.accountsystem.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.accountsystem.repository.TeacherRepository;
import ru.eltex.api.TeacherController;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private TeacherController teacherController;

    @Test
    public void contextLoads() {
        org.assertj.core.api.Assertions.assertThat(teacherController).isNotNull();
    }

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getTeacher() throws Exception {
//        Teacher teacher_test = null;
//        try {
//            Teacher teacher = new Teacher("login_test", "password_test", "email_test", "fio_test", Role.TEACHER, null);
//            teacherRepository.save(teacher);
//            teacher_test = teacherRepository.findByFio("fio_test");
//
//            this.mockMvc.perform(get("http://localhost:8089/teacher/" + teacher_test.getId())).andDo(print()).andExpect(status().isOk())
//                    .andExpect(content().string(containsString("Лучшая система учета студентов"))).andExpect(content().string(containsString("Обратная связь")))
//                    .andExpect(content().string(containsString("Ф.И.О.: fio_test"))).andExpect(content().string(containsString("Email: email_test")));
//        }
//        finally {
//            if(teacher_test.getId() != null) {
//                teacherRepository.deleteById(teacher_test.getId());
//            }
//        }
    }

    @Test
    public void getTeacherSubject() throws Exception {
//        String subjectId = null;
//        Teacher teacherTest = null;
//        try {
//            Subject subject = new Subject();
//            subject.setTitle("test_title");
//            subjectRepository.save(subject);
//            subjectId = subject.getId();
//            List<String> subjectsId = new ArrayList<>();
//            subjectsId.add(subjectId);
//            Teacher teacher = new Teacher("login_test", "password_test", "email_test", "fio_test", Role.TEACHER, subjectsId);
//            teacherRepository.save(teacher);
//            teacherTest = teacherRepository.findByFio("fio_test");
//            this.mockMvc.perform(get("http://localhost:8089/teacher/" + teacherTest.getId() + "/subjects")).andDo(print()).andExpect(status().isOk())
//                    .andExpect(content().string(containsString("Добавить предмет"))).andExpect(content().string(containsString("test_title")));
//        }
//        finally {
//            if (subjectId != null){
//                subjectRepository.deleteById(subjectId);
//            }
//            if(teacherTest.getId() != null ) {
//                teacherRepository.deleteById(teacherTest.getId());
//
//            }
//        }
    }
}
