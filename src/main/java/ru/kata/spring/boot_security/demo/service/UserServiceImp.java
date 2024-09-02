package ru.kata.spring.boot_security.demo.service;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.*;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Transactional(readOnly = true)
@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Логируем начало метода
        LOGGER.info("Запрос пользователя с именем: " + username);
        User user = findByUsername(username);

        // Проверка, найден ли пользователь
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);

        }
        LOGGER.info("Создание UserDetails для пользователя: " + username);
        LOGGER.info("Имя: " + user.getUsername());
        LOGGER.info("Пароль: " + user.getPassword());
        LOGGER.info("Роль: " + user.getAuthorities(user.getRoles()));
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(),
                user.getPassword(),
                user.getAuthorities(user.getRoles()));
    }


    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        userDao.save(user);
    }


}
