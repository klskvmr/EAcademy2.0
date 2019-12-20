
package ru.eltex.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.service.UserRegistrationService;

/**
 * Класс-контроллер для осуществления регистрации
 * @author Masis Alexey
 * @version v2.0
 */
@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {
    private final UserRegistrationService userRegistrationService;
   private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public RegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    /**
     * Метод для получения страницы регистрации <b>/registration</b>
     * @return имя возвращаемой страницы
     */
    @GetMapping(value = "/registration")
    public String registerPage() {
        logger.info("start registerPage()");
        logger.debug("response registration.html");
        return "registration";
    }

    /**
     * Метод для регистрации пользователя <b>/registration_user</b>
     * @return Статус ответа в виде строки
     */
    @RequestMapping(value = "/registration_user", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody JsonNode jsonNode) {
        logger.info("start registry"); // обычное информационное сообщение
        logger.debug("request " + jsonNode.toString());

        String result = userRegistrationService.register(jsonNode);

        logger.debug("response " + result);
        return result;
    }
}
