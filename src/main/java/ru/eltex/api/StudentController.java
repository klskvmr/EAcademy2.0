package ru.eltex.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.service.StudentService;

import java.util.List;

/**
 * Класс-контроллер студентов
 *
 * @author Maria Koloskova
 * @version v2.0
 */
@Controller
public class StudentController {
    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Метод для получения страницы студента <b>/student/{id}</b>
     *
     * @return Страница студента
     */
    @RequestMapping(value = "/student/{studentId}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("studentId") String studentId, Model modelStudent) {
        logger.info("start getStudent()");
        logger.debug("request id = " + studentId);
        logger.debug("response student_main");
        modelStudent.addAttribute("student", studentService.getStudentById(studentId));
        return "student_main";
    }

    /**
     * Метод для получение страницы дисциплин<b>/student/{studentId}/subjects</b>
     *
     * @return Страница с дисциплинами
     */
    @RequestMapping(value = "student/{studentId}/subjects", method = RequestMethod.GET)
    public String getSubjects(@PathVariable("studentId") String studentId, Model model) {
        logger.info("start getSubjects()");
        logger.debug("request studentId = " + studentId);
        logger.debug("response student_subjects");
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("subjects", studentService.getAllSubjects(studentId));
        return "student_subjects";
    }

    /**
     * Метод для получения заданий<b>/student/{studentId}/subjects/{subjectId}/tasks</b>
     *
     * @return Страница с предметами
     */
    @RequestMapping(value = "student/{studentId}/subjects/{subjectId}/tasks", method = RequestMethod.GET)
    public String getTasks(@PathVariable("studentId") String studentId, @PathVariable("subjectId") String subjectId, Model model) {
        logger.info("start getTasks()");
        logger.debug("request studentId = " + studentId + "subjectId = " + studentId);
        logger.debug("response student_tasks");

        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("tasks", studentService.getSubjectTasks(subjectId));
        return "student_tasks";
    }

    /**
     * Метод для получения страницы с тестами <b>/student/{studentId}/tests</b>
     *
     * @return Страница с тестами
     */
    @RequestMapping(value = "student/{studentId}/tests", method = RequestMethod.GET)
    public String getTests(@PathVariable("studentId") String studentId, Model model) {
        logger.info("start getTests()");
        logger.debug("request studentId = " + studentId);
        logger.debug("response student_tests");

        model.addAttribute("tests", studentService.getTests(studentId));

        return "student_tests";
    }

    /**
     * Метод для получения расписания <b>/student/{studentId}/table</b>
     *
     * @return Страница с расписанием
     */
    @RequestMapping(value = "student/{studentId}/table", method = RequestMethod.GET)
    public String getTable(@PathVariable("studentId") String studentId, Model model) {
        logger.info("start getTable()");
        logger.debug("request studentId = " + studentId);
        logger.debug("response student_timetable");

        model.addAttribute("table", studentService.getTableForStudent(studentId));
        return "student_timetable";
    }

//        @RequestMapping(value = "student_{studentId}_getTasks", method = RequestMethod.GET)
//    public String getAllTasksByOneSubject(@PathVariable("studentId") String studentId, Model model) {
//        model.addAttribute("tasks", studentService.getAllTasksByOneSubject(studentId));
//        return "student_tasks";
//    }

    //REST METHOD

    /**
     * Метод для получения дисциплин<b>/student/{studentId}/subjects</b>
     *
     * @return Список дисциплин
     * @see Subject#Subject()
     */
    @RequestMapping(value = "student/{studentId}/subjects", method = RequestMethod.POST)
    @ResponseBody
    public List<Subject> getSubjects(@PathVariable("studentId") String idStudent) {
        logger.info("start getSubjects()");
        logger.debug("request studentId = " + idStudent);

        List<Subject> subjects = studentService.getAllSubjects(idStudent);
        StringBuilder subjectsToString = new StringBuilder();
        for (Subject subject : subjects) {
            subjectsToString.append(subject);
            subjectsToString.append(" ");
        }
        logger.debug("response " + subjectsToString.toString());
        return subjects;
    }
}