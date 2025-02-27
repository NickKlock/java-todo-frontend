package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ToDoNotFoundException extends NoSuchElementException {

    public ToDoNotFoundException(String s) {
        super(s);
    }
}
