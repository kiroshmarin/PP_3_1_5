package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String show(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("roles", user
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return "user-info";
    }

}
