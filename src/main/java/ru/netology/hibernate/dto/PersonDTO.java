package ru.netology.hibernate.dto;

import lombok.Getter;

@Getter
public class PersonDTO {
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;
    private String cityOfLiving;

    public PersonDTO() {
    }
}
