package com.example.academix.exceptions;

public class NoteNotExist extends NotFoundException {
    public NoteNotExist(String message) {
        super(message);
    }
}
