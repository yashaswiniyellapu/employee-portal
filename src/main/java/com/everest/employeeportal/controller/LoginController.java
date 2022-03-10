package com.everest.employeeportal.controller;

import com.everest.employeeportal.security.User;
import com.everest.employeeportal.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public Map<String, String> index() {
        return Map.of("status", "up");
    }

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public User user(@AuthenticationPrincipal String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
