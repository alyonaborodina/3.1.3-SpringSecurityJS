package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;


@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/")
    public String hello() {
        return "user";
    }

}
