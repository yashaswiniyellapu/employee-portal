package com.everest.employeeportal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TemplateController {
    @GetMapping("/api/display")
    @PreAuthorize("isAuthenticated()")
    public String testValue() {
        return "display";
    }
}
