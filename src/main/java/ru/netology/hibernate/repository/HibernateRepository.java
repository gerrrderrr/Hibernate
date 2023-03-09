package ru.netology.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.entity.id.PersonId;

import java.util.List;
import java.util.Optional;

@Repository
public interface HibernateRepository extends JpaRepository<Person, PersonId> {

    @Query("SELECT p FROM Person p where lower(p.cityOfLiving) = lower(:city)")
    List<Person> getPersonsByCity(String city);

    @Query("SELECT p FROM Person p where p.age < :age order by p.age asc")
    List<Person> getPersonsYoungerGivenAge(int age);

    @Query("SELECT p FROM Person p where lower(p.name) = lower(:name) and lower(p.surname) = lower(:surname)")
    Optional<Person> getPersonsByNameAndSurname(String name, String surname);
}