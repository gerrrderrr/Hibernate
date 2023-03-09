package ru.netology.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.hibernate.entity.id.PersonId;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONS")
@Entity
@Data
@IdClass(PersonId.class)
public class Person {
    @Id
    @Column(nullable = false, length = 50)
    private String name;

    @Id
    @Column(nullable = false, length = 50)
    private String surname;

    @Id
    @Column(nullable = false)
    private int age;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Column(name = "city_of_living", nullable = false, length = 50)
    private String cityOfLiving;
}
