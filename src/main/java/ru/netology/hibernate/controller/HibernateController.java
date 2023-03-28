package ru.netology.hibernate.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.netology.hibernate.advice.response.HibernateResponse;
import ru.netology.hibernate.dto.PersonDTO;
import ru.netology.hibernate.entity.id.PersonId;
import ru.netology.hibernate.service.HibernateService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequestMapping(value = "/persons")
@RestController
public class HibernateController {
    private final HibernateService service;

    public HibernateController(HibernateService service) {
        this.service = service;
    }

    @RolesAllowed("ROLE_WRITE")
    @PostMapping("/create-new-person")
    public PersonDTO createNewPerson(@ModelAttribute PersonDTO dto) {
        return service.createNewPerson(dto);
    }

    @Secured("ROLE_READ")
    @GetMapping("/all-users")
    public List<PersonDTO> getAllPersons() {
        return service.getAllPersons();
    }

    @RolesAllowed("ROLE_WRITE")
    @PutMapping("/update-phone/{name}-{surname}-{age}")
    public HibernateResponse updatePersonPhone(@PathVariable String name,
                                               @PathVariable String surname,
                                               @PathVariable String age,
                                               @RequestParam String phoneNumber) {
        return service.updatePersonPhone(new PersonId(name, surname, Integer.parseInt(age)), phoneNumber);
    }

    @RolesAllowed("ROLE_WRITE")
    @PutMapping("/update-city/{name}-{surname}-{age}")
    public HibernateResponse updatePersonCity(@PathVariable String name,
                                              @PathVariable String surname,
                                              @PathVariable String age,
                                              @RequestParam String city) {
        return service.updatePersonCity(new PersonId(name, surname, Integer.parseInt(age)), city);
    }

    @PreAuthorize("hasRole('ROLE_DELETE')")
    @DeleteMapping("/delete/{name}-{surname}-{age}")
    public HibernateResponse deletePerson(@PathVariable String name,
                                          @PathVariable String surname,
                                          @PathVariable String age) {
        return service.deletePerson(new PersonId(name, surname, Integer.parseInt(age)));
    }

    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_READ')")
    @GetMapping("/by-city")
    public List<PersonDTO> getPersonsByCity(@RequestParam String city) {
        return service.getPersonsByCity(city);
    }

    @PreAuthorize("hasAnyRole('ROLE_READ', 'ROLE_WRITE')")
    @GetMapping("/by-age")
    public List<PersonDTO> getPersonsByAge(@RequestParam int age) {
        return service.getPersonsByAge(age);
    }

    @PreAuthorize("hasAnyRole('ROLE_DELETE', 'ROLE_WRITE')")
    @GetMapping("/by-name-surname")
    public List<PersonDTO> getPersonsByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return service.getPersonsByNameAndSurname(name, surname);
    }

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping("/get-roles")
    public List<String> getRoles(@RequestParam String username) {
        return service.getRoles(username);
    }

    @PostAuthorize("returnObject.username == #username")
    @GetMapping("/get-user")
    public User getUser(@RequestParam String username) {
        return service.getUser(username);
    }
}
