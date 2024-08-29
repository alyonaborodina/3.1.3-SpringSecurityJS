package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void add(User user);
    void update(User user);
    void delete(User user);
    List<User> findAll();
    User findById(Integer id);
    User findByUsername(String username);
    void createUser(String username, String password, Set<Role> roles);
    Role createRoles(String roleUser);
    Role findRoleByName(String name);
}
