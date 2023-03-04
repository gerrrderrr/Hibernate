package ru.netology.hibernate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.netology.hibernate.dto.PersonDTO;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.repository.HibernateRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HibernateService {
    private final HibernateRepository repository;

    public HibernateService(HibernateRepository repository) {
        this.repository = repository;
    }

    public List<PersonDTO> getPersonsByCity(String city) {
        List<Person> persons = repository.getPersonsByCity(city);
        return mapPersonToPersonDTO(persons);
    }

    private List<PersonDTO> mapPersonToPersonDTO(List<Person> persons) {
        ObjectMapper mapper = new ObjectMapper();
        return persons.stream().map(x -> mapper.convertValue(x, PersonDTO.class)).collect(Collectors.toList());
    }
}
