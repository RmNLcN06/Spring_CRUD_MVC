package com.rmnlcn.Spring_CRUD_MVC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    // Add request mapping for /registered-users
    @GetMapping("/registered-users")
    public String showRegisteredUsers() {
        return "roles/registered-users";
    }

    // Add request mapping for /admin-users
    @GetMapping("/admin-users")
    public String showAdminUsers() {
        return "roles/admin-users";
    }

}
