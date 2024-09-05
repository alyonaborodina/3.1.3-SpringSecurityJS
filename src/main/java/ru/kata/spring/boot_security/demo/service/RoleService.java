package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role add(String role);
    List<Role> findAll();
    Role findById(Long id);
    Set<Role> getRolesFromIds(Set<String> roleIds);
}
