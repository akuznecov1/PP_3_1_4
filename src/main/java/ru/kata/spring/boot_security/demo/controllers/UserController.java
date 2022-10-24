package ru.kata.spring.boot_security.demo.controllers;

import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String show(Principal principal, Model model) {
        model.addAttribute("user", userService.getByName(principal.getName()));
        return "/user/index";
    }
}
