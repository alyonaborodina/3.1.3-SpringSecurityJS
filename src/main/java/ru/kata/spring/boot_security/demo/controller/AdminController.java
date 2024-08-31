package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }


    @GetMapping(value ="/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedUser(@ModelAttribute("user") User user, @PathVariable Integer id) {
        user.setId(id);
        userService.save(user);
        return "redirect:/admin/";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@ModelAttribute("user") User user, @PathVariable Integer id) {
        user.setId(id);
        userService.delete(user);
        return "redirect:/admin/";
    }

}
