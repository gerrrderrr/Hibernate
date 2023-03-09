package ru.netology.hibernate.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.hibernate.advice.exceptions.PersonNotFound;
import ru.netology.hibernate.advice.response.HibernateResponse;

@RestControllerAdvice
public class HibernateExceptionHandlerAdvice {

    @ExceptionHandler(PersonNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HibernateResponse personNotFound(PersonNotFound e) {
        return new HibernateResponse(e.getMessage());
    }

}
