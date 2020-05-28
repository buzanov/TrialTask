package ru.itis.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.task.service.interfaces.VkAuthService;

@Controller
public class LoginController {
    @Autowired
    VkAuthService authService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    public String page(@RequestParam(value = "code", required = false) String code) {
        if ((SecurityContextHolder.getContext().getAuthentication() == null
                || SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser"))
                && code != null) {
            if (authService.auth(code)) {
                return "redirect:/account";
            } else
                return "redirect:/signIn";
        }
        return "login_page";
    }

}
