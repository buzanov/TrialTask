package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.task.model.User;
import ru.itis.task.repository.UserRepository;
import ru.itis.task.session.MySession;

import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MySession session;

    @GetMapping("/account")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile_page");
        modelAndView.addObject("user", session.getUser());
        return modelAndView;
    }

    @GetMapping("/account/{userId}")
    @RequestScope
    public ModelAndView profile(@PathVariable User userId) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("profile_page");
            modelAndView.addObject("user", userId);
//            modelAndView.addObject("user", optionalUser.get());
            return modelAndView;
//        } else {
//            return new ModelAndView("profile_page");
//        }
    }
}
