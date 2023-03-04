package ru.netology.hibernate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hibernate.dto.PersonDTO;
import ru.netology.hibernate.service.HibernateService;

import java.util.List;

@RestController
public class HibernateController {
    private final HibernateService service;

    public HibernateController(HibernateService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/persons/by-city")
    public List<PersonDTO> getPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }
}
