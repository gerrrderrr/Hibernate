package ru.netology.hibernate.service;

import org.springframework.stereotype.Service;
import ru.netology.hibernate.advice.exceptions.PersonNotFound;
import ru.netology.hibernate.advice.response.HibernateResponse;
import ru.netology.hibernate.dto.PersonDTO;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.entity.id.PersonId;
import ru.netology.hibernate.repository.HibernateRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HibernateService {
    private final HibernateRepository repository;
    private final PersonMapper mapper = new PersonMapper();

    public HibernateService(HibernateRepository repository) {
        this.repository = repository;
    }

    public PersonDTO createNewPerson(PersonDTO dto) {
        Person person = repository.save(mapper.mapPersonDtoToPersonEntity(dto));
        return mapper.mapPersonEntityToPersonDto(person);
    }

    public List<PersonDTO> getAllPersons() {
        List<Person> persons = repository.findAll();
        return mapper.mapListOfPersonToPersonDTO(persons);
    }

    public HibernateResponse updatePersonPhone(PersonId id, String phoneNumber) {
        Person person = getPerson(id);
        person.setPhoneNumber(phoneNumber);
        repository.save(person);
        return new HibernateResponse("Phone number was updated");
    }

    private Person getPerson(PersonId id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new PersonNotFound("Person: " +
                        id.getName() + " " +
                        id.getSurname() + " not found"));
    }

    public HibernateResponse updatePersonCity(PersonId id, String city) {
        Person person = getPerson(id);
        person.setCityOfLiving(city);
        repository.save(person);
        return new HibernateResponse("City was updated");
    }

    public HibernateResponse deletePerson(PersonId id) {
        Person person = getPerson(id);
        repository.delete(person);
        return new HibernateResponse("Person: " +
                id.getName() + " " +
                id.getSurname() + " was deleted");
    }

    public List<PersonDTO> getPersonsByCity(String city) {
        List<Person> persons = repository.findByCityOfLivingIgnoreCase(city);
        return mapper.mapListOfPersonToPersonDTO(persons);
    }

    public List<PersonDTO> getPersonsByAge(int age) {
        List<Person> persons = repository.findByAgeLessThanOrderByAgeAsc(age);
        return mapper.mapListOfPersonToPersonDTO(persons);
    }

    public List<PersonDTO> getPersonsByNameAndSurname(String name, String surname) {
        Optional<Person> persons = repository.findByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);
        List<PersonDTO> dto = null;
        if (persons.isPresent()) {
            dto = mapper.mapListOfPersonToPersonDTO(persons.stream().collect(Collectors.toList()));
        }
        return dto;
    }
}
