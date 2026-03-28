package com.example.academix.exceptions;

public class QuestionAlreadyExist extends AlreadyExistsException {
    public QuestionAlreadyExist(String message) {
        super(message);
    }
}
