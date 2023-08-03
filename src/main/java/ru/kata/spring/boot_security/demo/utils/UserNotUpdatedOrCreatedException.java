package ru.kata.spring.boot_security.demo.utils;

public class UserNotUpdatedOrCreatedException extends RuntimeException {

    public UserNotUpdatedOrCreatedException(String message) {
        super(message);
    }
}
