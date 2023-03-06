package ru.netology.hibernate.repository;

import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class HibernateRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Person> getPersonsByCity(String city) {
        Query query = entityManager
                .createNativeQuery("SELECT* FROM public.persons WHERE lower(city_of_living) = lower(:city_of_living)",
                        Person.class)
                .setParameter("city_of_living", city);
        return query.getResultList();
    }
}
