package com.aashish.todo_app_basic.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService auth;

    public LoginController(AuthenticationService auth) {
        this.auth = auth;
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String welcome(@RequestParam String name, @RequestParam String password, ModelMap model) {

        if (auth.authenticate(name, password)) {
            model.put("name", name);
            return "welcome";
        }
        model.put("errormsg", "Either username or password is incorrect!!");
        return "login";
    }
}
