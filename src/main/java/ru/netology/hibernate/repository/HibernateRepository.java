package ru.netology.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;
import ru.netology.hibernate.entity.id.PersonId;

import java.util.List;
import java.util.Optional;

@Repository
public interface HibernateRepository extends JpaRepository<Person, PersonId> {

    List<Person> findByCityOfLivingIgnoreCase(String city);

    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    Optional<Person> findByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);
}
