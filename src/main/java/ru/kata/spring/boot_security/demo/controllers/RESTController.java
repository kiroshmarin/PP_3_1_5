package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servicies.UserService;
import ru.kata.spring.boot_security.demo.utils.UserNotFoundException;
import ru.kata.spring.boot_security.demo.utils.UserNotUpdatedOrCreatedException;
import ru.kata.spring.boot_security.demo.utils.UserValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;
    private final UserValidator userValidator;

    public RESTController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.show(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            sendErrorResponse(bindingResult);
        }
        userService.add(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/users")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            sendErrorResponse(bindingResult);
        }
        userService.update(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        if (id == null) {
            throw new UserNotFoundException();
        }
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private void sendErrorResponse(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            sb.append(error.getDefaultMessage())
                    .append("; ")
                    .append("\r\n");

        }
        throw new UserNotUpdatedOrCreatedException(sb.toString());
    }

}
