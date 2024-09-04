package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface RoleDao {
    Role add(Role role);
    List<Role> findAll();
    Role findById(Long id);
}
