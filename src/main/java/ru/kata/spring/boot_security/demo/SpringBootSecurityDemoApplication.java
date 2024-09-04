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

import java.util.*;

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
            adminUser.setPassword("admin123");
            adminUser.setRoles(adminRoles);
            userService.save(adminUser);

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            User userUser = new User();
            userUser.setName("user");
            userUser.setPassword("user123");
            userUser.setRoles(userRoles);
            userService.save(userUser);

            User test = new User();
            test.setName("test");
            test.setPassword("test123");
            List<String> roleIdsTest = new ArrayList<>(Arrays.asList("1", "2"));
            Set<Role> rolesSet = new HashSet<>();
            for (String roleId : roleIdsTest) {
                Role role = roleService.findById(Long.parseLong(roleId));
                rolesSet.add(role);
            }
            test.setRoles(rolesSet);
            userService.save(test);

        };
    }
}

