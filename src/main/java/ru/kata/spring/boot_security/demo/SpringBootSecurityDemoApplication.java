package ru.kata.spring.boot_security.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            Role userRole = userService.add("ROLE_USER");
            Role adminRole = userService.add("ROLE_ADMIN");

            // Создание пользователей
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            userService.createUser("admin", "admin123", adminRoles);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            userService.createUser("user", "user123", userRoles);

        };
    }
}

