package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.task.model.User;
import ru.itis.task.repository.OrderRepository;
import ru.itis.task.repository.UserRepository;
import ru.itis.task.session.MySession;

import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MySession session;

    @GetMapping("/account")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile_page");
        modelAndView.addObject("user", session.getUser());
        modelAndView.addObject("orders", orderRepository.findAllByUserId(session.getUser().getId()));
        return modelAndView;
    }

    @GetMapping("/account/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
