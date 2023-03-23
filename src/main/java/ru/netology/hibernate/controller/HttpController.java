package ru.netology.hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HttpController {
    public HttpController() {
    }

    @GetMapping("/persons/create")
    public String create() {
        return "create_user";
    }
}
