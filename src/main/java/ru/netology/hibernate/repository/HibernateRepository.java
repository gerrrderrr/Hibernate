package ru.netology.hibernate.repository;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import ru.netology.hibernate.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void buildEntitiesOnStart() {
        entityManager.persist(Person.builder()
                .name("Anna")
                .surname("White")
                .age(23)
                .phoneNumber("79998887766")
                .cityOfLiving("Moscow")
                .build());
        entityManager.persist(Person.builder()
                .name("Eva")
                .surname("Black")
                .age(25)
                .phoneNumber("79998887755")
                .cityOfLiving("Ufa")
                .build());
        entityManager.persist(Person.builder()
                .name("Emmy")
                .surname("Blue")
                .age(20)
                .phoneNumber("79998887744")
                .cityOfLiving("Moscow")
                .build());
        entityManager.persist(Person.builder()
                .name("Tom")
                .surname("Pink")
                .age(22)
                .phoneNumber("79998887733")
                .cityOfLiving("Kazan")
                .build());
        entityManager.persist(Person.builder()
                .name("Jenny")
                .surname("Gray")
                .age(26)
                .phoneNumber("79998887722")
                .cityOfLiving("Moscow")
                .build());
    }
}
