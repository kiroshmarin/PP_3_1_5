package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servicies.RoleService;
import ru.kata.spring.boot_security.demo.servicies.AppUserDetailsService;
import ru.kata.spring.boot_security.demo.servicies.UserService;
import ru.kata.spring.boot_security.demo.utils.UserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;
    private final AppUserDetailsService appUserDetailsService;

    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator, AppUserDetailsService appUserDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
        this.appUserDetailsService = appUserDetailsService;
    }

    @GetMapping
    public String index(Principal principal, Model model) {
        model.addAttribute("authUser", appUserDetailsService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.index());
        model.addAttribute("rolesToAdd", roleService.getRoles());
        return "admin-panel";
    }

    @GetMapping("/new")
    public String showForm(@ModelAttribute User user, Model model) {
        model.addAttribute("rolesToAdd", roleService.getRoles());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("rolesToAdd", roleService.getRoles());
            return "new";
        }
        userService.add(user);
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("rolesToAdd", roleService.getRoles());
//        model.addAttribute("user", userService.show(id));
//        return "edit";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rolesToAdd", roleService.getRoles());
            return "admin-panel";
        }
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
