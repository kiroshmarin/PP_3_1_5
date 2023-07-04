package ru.kata.spring.boot_security.demo.servicies;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

public interface AppUserDetailsService extends UserDetailsService {


    User findByEmail(String email);
}
