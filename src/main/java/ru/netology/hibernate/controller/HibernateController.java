package ru.netology.hibernate.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.hibernate.advice.response.HibernateResponse;
import ru.netology.hibernate.dto.PersonDTO;
import ru.netology.hibernate.entity.id.PersonId;
import ru.netology.hibernate.service.HibernateService;

import java.util.List;

@RequestMapping(value = "/persons")
@RestController
public class HibernateController {
    private final HibernateService service;

    public HibernateController(HibernateService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public PersonDTO createNewPerson(@RequestBody PersonDTO dto) {
        return service.createNewPerson(dto);
    }

    @GetMapping("/all-users")
    public List<PersonDTO> getAllPersons() {
        return service.getAllPersons();
    }

    @PutMapping("/update-phone/{name}-{surname}-{age}")
    public HibernateResponse updatePersonPhone(@PathVariable String name,
                                               @PathVariable String surname,
                                               @PathVariable String age,
                                               @RequestParam String phoneNumber) {
        return service.updatePersonPhone(new PersonId(name, surname, Integer.parseInt(age)), phoneNumber);
    }

    @PutMapping("/update-city/{name}-{surname}-{age}")
    public HibernateResponse updatePersonCity(@PathVariable String name,
                                              @PathVariable String surname,
                                              @PathVariable String age,
                                              @RequestParam String city) {
        return service.updatePersonCity(new PersonId(name, surname, Integer.parseInt(age)), city);
    }

    @DeleteMapping("/delete/{name}-{surname}-{age}")
    public HibernateResponse deletePerson(@PathVariable String name,
                                          @PathVariable String surname,
                                          @PathVariable String age) {
        return service.deletePerson(new PersonId(name, surname, Integer.parseInt(age)));
    }

    @GetMapping("/by-city")
    public List<PersonDTO> getPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }

    @GetMapping("/by-age")
    public List<PersonDTO> getPersonsByAge(@RequestParam int age) {
        return service.getPersonsByAge(age);
    }

    @GetMapping("/by-name-surname")
    public List<PersonDTO> getPersonsByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return service.getPersonsByNameAndSurname(name, surname);
    }
}
