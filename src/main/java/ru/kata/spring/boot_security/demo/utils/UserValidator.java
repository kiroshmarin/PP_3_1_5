package ru.kata.spring.boot_security.demo.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

@Component
public class UserValidator implements Validator {

    private final UsersRepository usersRepository;

    public UserValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Этот Email/Логин занят, введите другой адрес");
        }
    }
}
