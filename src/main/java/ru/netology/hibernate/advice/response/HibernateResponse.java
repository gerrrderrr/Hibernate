package ru.netology.hibernate.advice.response;

import lombok.Getter;

@Getter
public class HibernateResponse {
    private final String message;

    public HibernateResponse(String message) {
        this.message = message;
    }
}
