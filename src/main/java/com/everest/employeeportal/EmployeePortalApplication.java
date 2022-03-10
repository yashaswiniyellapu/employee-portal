package com.everest.employeeportal;

import com.everest.employeeportal.security.User;
import com.everest.employeeportal.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EmployeePortalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmployeePortalApplication.class, args);
    }
    @Autowired
    private UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User adminUser = new User(null, "admin", passwordEncoder.encode("admin"), "ROLE_ADMIN");
        User normalUser = new User(null, "yashu", passwordEncoder.encode("yashu"), "ROLE_USER");
        repo.save(adminUser);
        repo.save(normalUser);

    }
}
