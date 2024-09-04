package ru.kata.spring.boot_security.demo.dao;



import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    @Transactional
    void saveNewUser(User user);

    @Transactional
    void updateUser(User user);

    void save(User user);
    void delete(Integer userId);
    List<User> findAll();
    User findById(Integer id);
    User findByUsername(String username);
}
