package ru.eltex.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.service.TestStructureService;

import java.util.List;

/**
 * Класс-контроллер тестов
 * @author VladimirT8520
 * @version v2.0
 */
@RestController
public class TestController {
    private final TestStructureService testStructureService;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    public TestController(TestStructureService testStructureService) {
        this.testStructureService = testStructureService;
    }

    /**
     * Метод для сохранения теста<b>/teacher/{id}/subject/{idSubject}/load_test/createTest</b>
     * @see TestStructure#TestStructure()
     */
    @RequestMapping(value = "/teacher/{id}/subjects/{idSubject}/load_test/createTest", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public void saveTest(@PathVariable("id") java.lang.String id, @PathVariable("idSubject") java.lang.String idSubject,
                         @RequestBody TestStructure request) {
        logger.info("start saveTest()");
        logger.debug("request id = " + id + "idSubject = " + idSubject + " TextStructure = " + request.toString());
        testStructureService.saveTest(request, idSubject);
    }

    /**
     * Метод для добавления теста <b>/get_users</b>
     * @return Тест
     * @see TestStructure#TestStructure()
     */
    @RequestMapping(value = "/loadTest", method = RequestMethod.POST)
    public TestStructure loadTest(@RequestBody String request) {
        logger.info("start loadTest()");
        logger.debug("request  = " + request);
        TestStructure testStructure = testStructureService.loadTest(request);
        logger.debug("response " + testStructure.toString());
        return testStructure;
    }

    /**
     * Метод для получения всех тестов <b>/getAllTests</b>
     * @return Список id тестов
     */
    @RequestMapping(value = "/getAllTests", method = RequestMethod.GET)
    public List<java.lang.String> getAllTests() {
        logger.info("start getAllTests()");
        List<java.lang.String> tests =  testStructureService.getAllTests();
        StringBuilder testsToString = new StringBuilder();
        for (String test: tests) {
            testsToString.append(test);
            testsToString.append(" ");
        }
        logger.debug("response  = " + testsToString.toString());
        return tests;
    }
}