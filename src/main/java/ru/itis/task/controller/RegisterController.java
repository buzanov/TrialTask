package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.task.dto.SignUpDto;
import ru.itis.task.model.Role;
import ru.itis.task.model.User;
import ru.itis.task.service.interfaces.SignUpService;

@Controller
public class RegisterController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SignUpService signUpService;

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public String render() {
        return "sign_up";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @PreAuthorize("permitAll()")
    public String register(SignUpDto form) {
        signUpService.signUp(User.builder()
                .login(form.getLogin())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(Role.valueOf(form.getRole()))
                .build());
        return "redirect:/signIn";
    }
}
