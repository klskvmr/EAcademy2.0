package ru.eltex.testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.testsystem.model.QuestionModel;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.repository.TestStructureRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestStructureService {
    private final TestStructureRepository testStructureRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TestStructureService(TestStructureRepository testStructureRepository, SubjectRepository subjectRepository) {
        this.testStructureRepository = testStructureRepository;
        this.subjectRepository = subjectRepository;
    }

    public void saveTest(TestStructure request, String idSubject) {
        Subject subject = subjectRepository.findById(idSubject).get();
        List<String> testNames = subject.getTestIds();
        testNames.add(request.getId());
        subject.setTestIds(testNames);
        subjectRepository.save(subject);
        testStructureRepository.save(request);
    }

    public TestStructure loadTest(String testId) { //метод для загрузки тестового задания для прохождения теста
        TestStructure testStructure = testStructureRepository.getById(testId);
        for (QuestionModel q : testStructure.getTest()) {
            q.setTrueAnswer(null);
        }
        return testStructure;
    }
    public TestStructure getTest(String testId) {
        TestStructure testStructure = testStructureRepository.getById(testId);
        return testStructure;
    }

    public List<String> getAllTests() {
        List<TestStructure> strings = testStructureRepository.findAll();
        List<String> testNames = new ArrayList<>();
        strings.forEach(p -> testNames.add(p.getName()));
        return testNames;
    }
}
