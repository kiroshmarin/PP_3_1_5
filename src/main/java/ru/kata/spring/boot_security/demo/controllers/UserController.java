package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.security.AppUserDetails;
import ru.kata.spring.boot_security.demo.servicies.UserService;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String show(Authentication authentication, Model model) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("roles", userDetails
                .getUser()
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return "user-info";
    }

}
