package ru.eltex.accountsystem.service;

import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;

import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.repository.GroupRepository;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.repository.SubjectRepository;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public void addGroup(String idSubject, Group group) {
        Subject subject = subjectRepository.findById(idSubject).get();
        subject.getGroupIds().add(group.getId());
        group.getStudentIds().stream().forEach(studentId->
        {
            Student student= studentRepository.findById(studentId).get();
            student.setGroupId(group.getId());
            student.getSubjectIds().add(idSubject);

            studentRepository.save(student);
        });
        subjectRepository.save(subject);
        groupRepository.save(group);
    }

    public Group getGroup(String id) {
        return groupRepository.findById(id).get();
    }

    public void addStudent(String idGroup, String studentId) {
        Student student = studentRepository.findById(studentId).get();
        Group group = getGroup(idGroup);
        group.getStudentIds().add(student.getId());
        groupRepository.save(group);
        //заполнение у студента subjects
    }
}