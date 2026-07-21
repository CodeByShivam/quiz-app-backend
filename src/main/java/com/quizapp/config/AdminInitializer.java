package com.quizapp.config;

import com.quizapp.entity.Role;
import com.quizapp.entity.User;
import com.quizapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (!userRepository.existsByUsername("admin")) {

            User admin = new User();

            admin.setUsername("admin");

            admin.setPassword(passwordEncoder.encode("admin123"));

            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("=================================");
            System.out.println("Default Admin Created");
            System.out.println("Username : admin");
            System.out.println("Password : admin123");
            System.out.println("=================================");

        } else {

            System.out.println("Admin already exists.");

        }
    }
}