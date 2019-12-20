package ru.eltex.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.service.GroupService;
import ru.eltex.accountsystem.service.StudentService;
import ru.eltex.accountsystem.service.TeacherService;

/**
 * Класс-контроллер учителя
 *
 * @author Arina Nedobitkova
 * @version v2.0
 */
@Controller
public class TeacherController {
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    public TeacherController(TeacherService teacherService, GroupService groupService, StudentService studentService) {
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    /**
     * Метод для получения станицы учителя<b>/teacher/{idTeacher}</b>
     *
     * @return Страница учителя
     */
    @RequestMapping(value = "/teacher/{idTeacher}", method = RequestMethod.GET)
    public String getTeacher(@PathVariable("idTeacher") String idTeacher, Model modelTeacher) {
        logger.info("start getTeacher()");
        logger.debug("request id = " + idTeacher);
        logger.debug("response student_main");

        modelTeacher.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        return "teacher_main";
    }

    /**
     * Метод для получения станицы дисциплин<b>/teacher/{idTeacher}/subjects</b>
     *
     * @return Страница дисциплин
     */
    @RequestMapping(value = "/teacher/{idTeacher}/subjects", method = RequestMethod.GET)
    public String getTeacherSubjects(@PathVariable("idTeacher") String idTeacher, Model model) {
        logger.info("start getTeacherSubjects()");
        logger.debug("request teacherId = " + idTeacher);
        logger.debug("response teacher_subjects");
        model.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        model.addAttribute("subjects", teacherService.getTeacherSubjects(idTeacher));
        return "teacher_subjects";
    }

    /**
     * Метод для получения групп, подписанных на дисциплины <b>/teacher/{idTeacher}/subjects/{idSubject}</b>
     *
     * @return Станица с дисциплинами
     */
    @RequestMapping(value = "/teacher/{idTeacher}/subject/{idSubject}", method = RequestMethod.GET)
    public String getSubjectGroups(@PathVariable("idTeacher") String idTeacher, @PathVariable("idSubject") String idSubject, Model model) {
        logger.info("start getSubjectGroups()");
        logger.debug("request teacherId = " + idTeacher);
        logger.debug("response teacher_sbjct_grps");
        model.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        model.addAttribute("subjects", teacherService.getTeacherSubjects(idTeacher));
        model.addAttribute("students", studentService.getAllStudentWithoutGroup());
        model.addAttribute("groups", teacherService.getTeacherGroupsBySubject(idTeacher, idSubject));
        return "teacher_sbjct_grps";
    }

    @RequestMapping(value = "/teacher/{idTeacher}/groups", method = RequestMethod.GET)
    public String getTeacherGroups(@PathVariable("idTeacher") String idTeacher, Model model) {
        logger.info("start getTeacherGroups()");
        logger.debug("request id = " + idTeacher);
        logger.debug("response teacher_groups");

        model.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        model.addAttribute("subjects", teacherService.getTeacherSubjects(idTeacher));
        model.addAttribute("groups", teacherService.getTeacherGroups(idTeacher));
//        model.addAttribute("students", studentService.getAllStudentWithoutGroup());

        return "teacher_groups";
    }

    /**
     * Метод для получения страницы студентов из группы <b>/teacher_{idTeacher}_getStudentsFromGroup_{idGroup}</b>
     *
     * @return Сттаница с группами студентов
     */
    @RequestMapping(value = "/teacher/{idTeacher}/subjects/{idSubject}/group/{idGroup}", method = RequestMethod.GET)
    public String getStudentsFromGroup(@PathVariable("idTeacher") String id, @PathVariable("idGroup") String idGroup,
                                       @PathVariable("idSubject") String idSubject, Model modelStudents) {
        logger.info("start getStudentsFromGroup()");
        logger.debug("request id = " + id);
        logger.debug("response teacher_students_from_group");
        modelStudents.addAttribute("students", teacherService.getStudentsFromGroup(idGroup));
        modelStudents.addAttribute("teacher", teacherService.getTeacher(id));
        modelStudents.addAttribute("subject", idSubject);
        return "teacher_students";
    }
    
    //REST METHODS

    /**
     * Метод для получения учителя <b>/teacher/{id}/getInfo"</b>
     *
     * @return Учитель
     * @see Teacher#Teacher()
     */
    @GetMapping(value = "/teacher/{id}/getInfo")
    @ResponseBody
    public Teacher getTeacherInfo(@PathVariable("id") String id) {
        logger.info("start getTeacherInfo()");
        logger.debug("request id = " + id);

        Teacher teacher = teacherService.getTeacher(id);

        logger.debug("response " + teacher);
        return teacher;
    }

    /**
     * Метод для добавления группы<b>/teacher/{id}/subjects/{idSubject}/add_group</b>
     *
     * @return Статус ответа
     * @see Group#Group()
     */
    @RequestMapping(value = "/teacher/{id}/subjects/{idSubject}/add_group", method = RequestMethod.POST)
    @ResponseBody
    public String addGroup(@PathVariable("idSubject") String idSubject, @RequestBody Group group) {
        logger.info("start addGroup()");
        logger.debug("request idSubject = " + idSubject + "Group " + group.toString());
        groupService.addGroup(idSubject, group);
        //если group.students != null заполнение у студентов subjects
        return "cool";
    }

    /**
     * Метод для добавления студента в группу <b>/addStudent/{groupId}/{studentId}</b>
     */
    @RequestMapping(value = "/addStudent/{groupId}/{studentId}", method = RequestMethod.POST)
    @ResponseBody
    public void addStudentInGroup(@PathVariable("groupId") String groupId, @PathVariable("studentId") String studentId) {
        logger.info("start addStudentInGroup()");
        logger.debug("request groupId = " + groupId + "studentId = " + studentId);
        groupService.addStudent(groupId, studentId);
        studentService.addSubjectForStudent(studentId);
        //заполнение у студента subjects
    }

    /**
     * Метод для добавления дисциплины в группу <b>/teacher/{teacherId}/subjects/add_subject</b>
     *
     * @return id дисциплины
     */
    @RequestMapping(value = "/teacher/{teacherId}/subjects/add_subject", method = RequestMethod.POST)
    @ResponseBody
    public String addSubject(@PathVariable("teacherId") String teacherId, @RequestBody JsonNode subject) {
        logger.info("start addSubject()");
        logger.debug("request subject = " + subject.toString());
        return teacherService.addSubject(teacherId, subject);
    }

    /**
     * Метод для добавления оценки к заданию <b>/addScores/{studentId}/{taskId}/{scores}/{status}</b>
     */
    @RequestMapping(value = "/addScores/{studentId}/{taskId}/{scores}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public void addScores(@PathVariable("studentId") String studentId,
                          @PathVariable("taskId") String taskId,
                          @PathVariable("scores") Integer scores,
                          @PathVariable("status") String status) {
        logger.info("start addScores()");
        logger.debug("request idStudent = " + studentId + "taskId = " + taskId
                + " scores = " + scores + " status = " + status);
        teacherService.addScores(studentId, taskId, status, scores);
    }

    /**
     * Метод для добавления задания<b>/teacher_{teacherId}_subjects_{subjectId}_addTask</b>
     *
     * @see Task#Task(String, String, Integer)
     */
    @RequestMapping(value = "/teacher/{teacherId}/subjects/{subjectId}/add_task", method = RequestMethod.POST)
    public String addTask(@PathVariable("teacherId") String teacherId,
                          @PathVariable("subjectId") String subjectId,
                          @RequestBody JsonNode task) {
        logger.info("start addTask()");
        logger.debug("request teacherId = " + teacherId + " subjectId = " + subjectId);
        return teacherService.addTask(teacherId, subjectId, task);
    }

    /**
     * Метод для получения ответов на задания<b>/teacher_{teacherId}_subjects_{subjectId}_{groupId}_TasksResults</b>
     *
     * @return Страница с ответами на задания
     */
    @RequestMapping(value = "teacher/{teacherId}/subjects/{subjectId}/{studentId}/TasksResults", method = RequestMethod.GET)
    public String getTasksResults(@PathVariable("teacherId") String teacherId, @PathVariable("subjectId") String subjectId,
                                  @PathVariable("studentId") String studentId, Model model) {
        logger.info("start getTasksResults()");
        logger.debug("request idGroup = " + teacherId);
        logger.debug("response teacher_tasks_results");
        model.addAttribute("tasksResults", teacherService.getTasks(subjectId, studentId)); // добавление всех результатов тестов конкретной группы
        return "teacher_tasks_results";
    }
}