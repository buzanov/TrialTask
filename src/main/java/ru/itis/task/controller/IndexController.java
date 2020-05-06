package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.task.repository.UserRepository;
import ru.itis.task.session.MySession;

@Controller
public class IndexController {
    @Autowired
    MySession session;

    @GetMapping("/")
    public String indexPage() {
        if (session.getUser() != null) {
            return "redirect:/account";
        }
        return "redirect:/signIn";
    }
}
