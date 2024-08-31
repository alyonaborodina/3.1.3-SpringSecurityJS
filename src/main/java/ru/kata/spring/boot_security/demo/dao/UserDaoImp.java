package ru.kata.spring.boot_security.demo.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(User user) {
        if (user.getUsername() == null) {
            this.entityManager.persist(user);
        } else {
            this.entityManager.merge(user);
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
        return null;
    }

}

