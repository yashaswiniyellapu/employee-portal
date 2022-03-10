package com.everest.employeeportal.controller;

import com.everest.employeeportal.jwt.ValidTokenCreator;
import com.everest.employeeportal.login.LoginRequest;
import com.everest.employeeportal.login.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final ValidTokenCreator validToken;

    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponse loginResponse = new LoginResponse();
        Date expiresAt = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        final String token = validToken.generateToken(authentication.getName());
        loginResponse.setAccessToken(token);
        loginResponse.setExpiresAt(expiresAt);
        return loginResponse;
    }
}
