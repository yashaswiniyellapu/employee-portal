package com.everest.employeeportal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/api/")
public class CustomLogin {
    @GetMapping("login")
    public String loginPage()
    {
        return "login";
    }
    @GetMapping("role")
    public String displayPage()
    {
        return "display";
    }

}
