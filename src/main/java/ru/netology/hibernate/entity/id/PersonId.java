package ru.netology.hibernate.entity.id;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class PersonId implements Serializable {
    private String name;
    private String surname;
    private int age;
}
