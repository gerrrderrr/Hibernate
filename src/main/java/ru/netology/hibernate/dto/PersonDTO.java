package ru.netology.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;
    private String cityOfLiving;

    public PersonDTO() {
    }
}
