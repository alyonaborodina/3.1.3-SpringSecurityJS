package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveNewUser(User user) {
        this.entityManager.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.entityManager.merge(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        if (user.getUsername() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));// Проверка на существование пользователя
            saveNewUser(user);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            updateUser(user);
        }
    }


    @Override
    public void delete(User user) {
        if (!entityManager.contains(user)) {
            user = entityManager.find(User.class, user.getId());
        }
        entityManager.remove(user);
    }


    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }


    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();

    }

}

