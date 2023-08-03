package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String index(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("authUser", user);
        return "admin";
    }
}
