package ru.eltex.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.service.StudentService;
import ru.eltex.accountsystem.service.TeacherService;
import ru.eltex.accountsystem.service.UserService;

import java.security.Principal;

/**
 * Класс-контроллер юзеров
 * @author Artem
 * @version v2.0
 */
@Controller
public class UserController {
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(TeacherService teacherService, StudentService studentService, UserService userService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.userService = userService;
    }

    /**
     * Метод для сопоставлением со страницей авторизации<b>/</b>
     * @return Сттаница авторизации
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get() {
        logger.info("start get()");
        logger.debug("response authorization");
        return "authorization";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    /**
     * Метод для получения пользователя <b>/user_{id}</b>
     * @return Массив объектов пользователей
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(Principal principal, Model modelUser) {
        logger.info("start getUser()");
        logger.debug("request id = " + principal.getName());
        UserRole userRole = userService.getUserRole(principal.getName());
        if (userRole.getAuthorities().get(0).equals(Role.TEACHER)) {
            logger.debug("response teacher_main");
            modelUser.addAttribute("teacher", teacherService.getTeacher(userRole.getUserId()));
            return "redirect:/teacher/"+userRole.getUserId();
        } else {
            logger.debug("response student_main");
            modelUser.addAttribute("student", studentService.getStudentById(userRole.getUserId()));
            return "redirect:/student/" + userRole.getUserId();
        }
    }
}