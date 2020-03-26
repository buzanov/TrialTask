package ru.itis.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String page() {
        return "login_page";
    }
}
