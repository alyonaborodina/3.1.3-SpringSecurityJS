package ru.kata.spring.boot_security.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        return args -> {
            Role userRole = roleService.add("ROLE_USER");
            Role adminRole = roleService.add("ROLE_ADMIN");

            // Создание пользователей
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            User adminUser = new User();
            adminUser.setName("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRoles(adminRoles);
            userService.save(adminUser);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            User userUser = new User();
            userUser.setName("user");
            userUser.setPassword(passwordEncoder.encode("user123"));
            userUser.setRoles(userRoles);
            userService.save(userUser);

        };
    }
}

