package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void delete(Integer id);
    List<User> findAll();
    User findById(Integer id);
    User findByUsername(String username);
    void save(User user);
}
