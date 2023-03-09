package ru.netology.hibernate.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.netology.hibernate.dto.PersonDTO;
import ru.netology.hibernate.entity.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {
    private final ObjectMapper mapper = new ObjectMapper();

    public PersonMapper() {
    }

    public Person mapPersonDtoToPersonEntity(PersonDTO dto) {
        return mapper.convertValue(dto, Person.class);
    }

    public PersonDTO mapPersonEntityToPersonDto(Person person) {
        return mapper.convertValue(person, PersonDTO.class);
    }

    public List<PersonDTO> mapListOfPersonToPersonDTO(List<Person> persons) {
        return persons.stream().map(x -> mapper.convertValue(x, PersonDTO.class)).collect(Collectors.toList());
    }
}
