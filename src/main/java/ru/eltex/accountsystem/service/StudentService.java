package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.*;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.accountsystem.repository.TaskRepository;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.repository.TestStructureRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сервис
 * @author Koloskova Maria
 * @version v2.0
 */

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TaskRepository taskRepository;
    private final TestStructureRepository testStructureRepository;
    private final TableService tableService;

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, TaskRepository taskRepository, TestStructureRepository testStructureRepository, TableService tableService) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.taskRepository = taskRepository;
        this.testStructureRepository = testStructureRepository;
        this.tableService = tableService;
    }

    public Student getStudentById(String idStudent) {
        return studentRepository.findById(idStudent).get();
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    public List<Student> getAllStudentWithoutGroup() {
        List<Student> students=getAllStudent();
        List<Student> studentsWithoutGroup=new ArrayList<>();
        students.stream().filter(student -> student.getGroupId().equals("")).forEach(student -> studentsWithoutGroup.add(student));
        return studentsWithoutGroup;
    }
    public List<Subject> getAllSubjects(String idStudent) {
        List<Subject> subjects = new ArrayList<>();

        List<String> subjectIds = getStudentById(idStudent).getSubjectIds();
        subjectIds.forEach(idSubject -> subjects.add(subjectRepository.findById(idSubject).get()));

        return subjects;
    }

//    public Map<Subject, Integer> getMarks(String idStudent) {
//        Map<Subject, Integer> marks = new HashMap<>();
//        ArrayList<Subject> subjects = new ArrayList<>();
//        getStudentById(idStudent).getSubjects().forEach(elem-> subjects.add(subjectRepository.findById(elem).get()));
//        for (Subject subject : subjects) {
//            Integer mark = 0;
//            ArrayList<TaskResult> tasksResult = new ArrayList<>();
//            subject.getTaskIds().stream().forEach(elem-> tasksResult.add(taskResultRepository.findByIdTask(elem)));
//
//            for(TaskResult elem: tasksResult) {
//                mark+= elem.getScores();
//            }
//
//            ArrayList<TestResult> testResults = new ArrayList<>();
//            subject.getTestIds().forEach(elem-> testResults.add(testResultRepository.findByTestId(elem)));
//
//            for(TestResult elem: testResults) {
//                mark+= elem.getResult();
//            }
//
//            marks.put(subject, mark);
//        }
//        return marks;
//    }

    public Subject getSubjectById(String idSubject) {
        return subjectRepository.findById(idSubject).get();
    }

    public List<String> getSubjectTasksIds(String idSubject) {
        Subject subject = subjectRepository.findById(idSubject).get();
        return subject.getTaskIds();
    }

    public List<Task> getSubjectTasks(String idSubject) {
        List<Task> tasks = new ArrayList<>();
        List<String> taskIds = getSubjectTasksIds(idSubject);
        taskIds.forEach(taskId -> tasks.add(taskRepository.findById(taskId).get()));
        return tasks;
    }

    public List<TestStructure> getTests(String idStudent) {
        ArrayList<Subject> subjects = new ArrayList<>();
        getStudentById(idStudent).getSubjectIds().forEach(elem -> subjects.add(subjectRepository.findById(elem).get()));

        List<TestStructure> tests = new ArrayList<>();

        for (Subject subject : subjects) {
            subject.getTestIds().forEach(elem -> tests.add(testStructureRepository.findById(elem).get()));
        }

        return tests;
    }

    public void addSubjectForStudent(String studentId) {
        ArrayList<String> subjects = new ArrayList<>();
        Student student = studentRepository.findById(studentId).get(); //Студент находится в репозитории но его предметы пока еще не заполнены
        //заполнение предметов у студента: получаем все предметы, затем у каждого предмета получаем список групп и если в этом списке
        // присутсвует группа с grId то добавляем id предмета к списку id предметов студента.

        subjectRepository.findAll().forEach(subject -> subject.getGroupIds().stream().filter(groupId -> groupId.equals(student.getGroupId())).forEach(elem2 -> subjects.add(subject.getId())));
        student.setSubjectIds(subjects);
        studentRepository.save(student);
    }

    public Table getTableForStudent(String studentId) {
        Student student = getStudentById(studentId);
        return tableService.loadTable(student.getGroupId());
    }
}