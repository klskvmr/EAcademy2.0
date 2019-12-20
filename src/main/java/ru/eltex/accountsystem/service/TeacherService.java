package ru.eltex.accountsystem.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.*;
import ru.eltex.accountsystem.enums.TaskStatus;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.TaskResult;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final TaskResultRepository taskResultRepository;
    private final TaskRepository taskRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public TeacherService(TeacherRepository _repository, StudentRepository studentRepository, SubjectRepository subjectRepository,
                          GroupRepository groupRepository, TaskResultRepository taskResultRepository, TaskRepository taskRepository) {
        this.teacherRepository = _repository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.taskResultRepository = taskResultRepository;
        this.taskRepository = taskRepository;
    }

    public Teacher getTeacher(String id) {
        return teacherRepository.findById(id).get();
    }

    public List<String> getTeacherSubjectIds(String idTeacher) {
        Teacher teacher = teacherRepository.findById(idTeacher).get();
        return teacher.getSubjectIds();
    }

    public List<Subject> getTeacherSubjects(String idTeacher) {
        List<String> subjectIds = getTeacherSubjectIds(idTeacher);
        List<Subject> subjects = new ArrayList<>();
        subjectIds.forEach(subjectId -> subjects.add(subjectRepository.findById(subjectId).get()));

        return subjects;
    }

    public List<Group> getTeacherGroups(String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects = new ArrayList<>();

        teacher.getSubjectIds().stream().forEach(subjectId -> {
            teacherSubjects.add(subjectRepository.findById(subjectId).get());
        });

        List<Group> groups = new ArrayList<>();
        List<String> groupIds = new ArrayList<>();
        teacherSubjects.forEach(sub -> groupIds.addAll(sub.getGroupIds()));

        groupIds.forEach(groupId -> groups.add(groupRepository.findById(groupId).get()));
        return groups;
    }

    public List<Group> getTeacherGroupsBySubject(String id,String idSubject) {
        Subject teacherSubject = subjectRepository.findById(idSubject).get();
        List<Group> groups = new ArrayList<>();
        teacherSubject.getGroupIds().forEach(group -> groups.add(groupRepository.findById(group).get()));
        return groups;
    }

    public List<Group> getSubjectGroups(String idSubject) {
        Subject subject = subjectRepository.findById(idSubject).get();
        List<Group> groups = new ArrayList<>();
        subject.getGroupIds().forEach(groupId -> groups.add(groupRepository.findById(groupId).get()));
        return groups;
    }

    public List<Student> getStudentsFromGroup(String idGroup) {
        Group group = groupRepository.findById(idGroup).get();
        List<Student> students = new ArrayList<>();
        group.getStudentIds().forEach(studentId -> students.add(studentRepository.findById(studentId).get()));
        return students;
    }

    public String addSubject(String teacherId,JsonNode jsonNode) {
        Map<String,String> subjectName=objectMapper.convertValue(jsonNode,Map.class);
        Subject subject=new Subject();
        subject.setTitle(subjectName.get("subjectName"));
        subject.setGroupIds(new ArrayList<>());
        subject.setTaskIds(new ArrayList<>());
        subject.setTestIds(new ArrayList<>());

        Teacher teacher=teacherRepository.findById(teacherId).get();
        teacher.getSubjectIds().add(subject.getId());
        teacherRepository.save(teacher);
        subjectRepository.save(subject);
        return subject.getId();
    }

    public void addScores(String studentId, String taskId, String status, Integer scores) {
        TaskResult task = taskResultRepository.findAll()
                .stream()
                .filter(tsk -> tsk.getIdTeacherTask().equals(taskId) && tsk.getIdStudent().equals(studentId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        task.setScores(scores);
        task.setTaskStatus(TaskStatus.valueOf(status));
        taskResultRepository.save(task);
    }

    public String addTask(String teacherId, String subjectId, JsonNode jsonNode) {
        Map<String,String> taskName = objectMapper.convertValue(jsonNode,Map.class);
        Task task=new Task();
        task.setTitle(taskName.get("taskName"));
        task.setDescription(taskName.get("description"));
        task.setMaxScores(Integer.parseInt(taskName.get("maxScores")));

        Subject subject = subjectRepository.findById(subjectId).get();
        List<String> tasksIdList = subject.getTaskIds();
        taskRepository.save(task);
        tasksIdList.add(task.getId());
        subject.setTaskIds(tasksIdList);
        subjectRepository.save(subject);
        List<String> listGroupsId = subject.getGroupIds();

        for (String idGroup : listGroupsId) {  //добавление тасок всем студентам которые есть в группах пренадлежащих переданному в метод предмету
            Group group = groupRepository.findById(idGroup).get();
            List<String> studentsId = group.getStudentIds();
            for (String idStudent : studentsId) {
                TaskResult taskResult = new TaskResult(teacherId, idStudent, null, null);
                taskResultRepository.save(taskResult);
            }
        }

        return task.getId();
    }

    public List<TaskResult> getTasks(String subjectId, String studentId) {
        List<String> taskIds = subjectRepository.findById(subjectId).get().getTaskIds();
        List<TaskResult> taskResults = new ArrayList<>();
        taskIds.forEach(taskId -> {
            var results = taskResultRepository.findByIdTeacherTask(taskId);
            taskResults.addAll(results);
        });
        taskResults.stream().filter(taskResult -> taskResult
                .getIdStudent().equals(studentId)).collect(Collectors.toList());
        return taskResults;
    }
}