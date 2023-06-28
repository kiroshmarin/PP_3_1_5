package ru.kata.spring.boot_security.demo.servicies;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
}
