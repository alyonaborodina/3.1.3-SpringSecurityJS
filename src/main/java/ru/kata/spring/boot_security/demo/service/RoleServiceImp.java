package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
@Service
public class RoleServiceImp implements RoleService {


    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public Role add(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleDao.add(role); // Возвращаем созданную роль
    }

    @Transactional
    public Set<Role> getRolesFromIds(Set<String> roleIds) {
        Set<Role> rolesSet = new HashSet<>();
        for (String roleId : roleIds) {
            Role role = roleDao.findById(Long.parseLong(roleId));
            rolesSet.add(role);
        }
        return rolesSet;
    }

    @Transactional
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }


}
