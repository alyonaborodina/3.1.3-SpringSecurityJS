package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

@Transactional(readOnly = true)
@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    public Role add(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleDao.add(role); // Возвращаем созданную роль
    }


}
