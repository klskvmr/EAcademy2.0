package ru.eltex.testsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.service.TestResultService;
import ru.eltex.testsystem.model.TestAnswers;
import ru.eltex.testsystem.service.TestStructureService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestAPI {
    private final TestStructureService testStructureService;
    private final TestResultService testResultService;

    @Autowired
    public TestAPI(TestStructureService testStructureService, TestResultService testResultService) {
        this.testStructureService = testStructureService;
        this.testResultService = testResultService;
    }

    @GetMapping(value = "/teacher/{idTeacher}/subjects/{idSubject}/load_test")
    public String index() {
        return "teacher_file_upload";
    }


    @RequestMapping(value = "/student/{id}/tests/{testId}", method = RequestMethod.GET)
    public String showtest(Model model, @PathVariable("id") String id, @PathVariable("testId") String testId) {
        model.addAttribute("testmodel", testStructureService.loadTest(testId));
        testResultService.initTestResult(id, testId); // Если в БД нет Такого TestResult то создаем его в БД
        TestAnswers testCurrentAnswers = testResultService.getTestResult(id, testId).getTestCurrentAnswers();
        model.addAttribute("testResult", testResultService.getTestResult(id, testId));
        model.addAttribute("testAnswers", testCurrentAnswers);
        model.addAttribute("testmodelFinal", testStructureService.getTest(testId));

        System.out.println(testCurrentAnswers);
        //TODO вместо true вставить перем.теста из шаблона
        // отвеч за повторное прохождение (true- запрет повторного) !!!!!!!!*******
        if (testResultService.getTestResult(id, testId).getTestFinishAnswers() != null && true) {
            model.addAttribute("badAnswers", testResultService.checkBadAnswers(id,testId));

            return "test_alredy_done";
        }
        return "test_form";
    }

    @RequestMapping(value = "/student/{id}/{testId}/saveCurrentResult", method = RequestMethod.POST)
    @ResponseBody
    public void addGroup(@RequestBody TestAnswers testCurrentAnswers, @PathVariable("id") String id
            , @PathVariable("testId") String idTest) {
        System.out.println(testCurrentAnswers);
        System.out.println("Внести в БД текущие данные теста");
        testResultService.setTestCurrentAnswers(id, idTest, testCurrentAnswers);
    }

    @PostMapping("/student/{id}/{testId}/finishtest")
    public String getDataTest(Model model, @ModelAttribute("testAnswers") TestAnswers testAnswers,
                              @PathVariable("id") String id, @PathVariable("testId") String testId) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!  ТЕСТ ЗАВЕРШЕН  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(testAnswers.toString());
        testResultService.setTestResult(id, testId, testAnswers);
        model.addAttribute("badAnswers", testResultService.checkBadAnswers(id,testId));
        model.addAttribute("testmodelFinal", testStructureService.getTest(testId));
        model.addAttribute("testResult", testResultService.getTestResult(id, testId));
        return "test_result";
    }

}
