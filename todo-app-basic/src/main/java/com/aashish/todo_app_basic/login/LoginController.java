package com.aashish.todo_app_basic.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginController {

//    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String login(@RequestParam String name, ModelMap model) {
    public String login() {
//        model.put("name", name);
//        logger.info("param is " + name);
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String welcome(@RequestParam String name, @RequestParam String password, ModelMap model) {
        model.put("name", name);
        model.put("pass", password);
        return "welcome";
    }
}
