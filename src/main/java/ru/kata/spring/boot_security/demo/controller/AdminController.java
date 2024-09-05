package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@RequestMapping("/admin")
@Controller
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "users"; // Используем один шаблон для всех вкладок
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("roleIds") Set<String> roleIds) {
        Set<Role> rolesSet = roleService.getRolesFromIds(roleIds);
        user.setRoles(rolesSet);
        userService.save(user);
        return "redirect:/admin/";
    }


    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "users"; // Используем тот же шаблон для редактирования
    }

    @PostMapping("/edit/{id}")
    public String saveEditedUser(@Valid User user, BindingResult bindingResult,
                                 @PathVariable Integer id, @RequestParam(value = "selectedRoles") Set<String> selectedRoles) {
        Set<Role> rolesSet = roleService.getRolesFromIds(selectedRoles);
        user.setRoles(rolesSet);
        userService.save(user);
        return "redirect:/admin/";
    }



    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/admin/";
    }


}
