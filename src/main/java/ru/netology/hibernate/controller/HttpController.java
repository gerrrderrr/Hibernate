package ru.netology.hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class HttpController {
    public HttpController() {
    }

    @RolesAllowed("ROLE_WRITE")
    @GetMapping("/persons/create")
    public String create() {
        return "create_user";
    }
}
