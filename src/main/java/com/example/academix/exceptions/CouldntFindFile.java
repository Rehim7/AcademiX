package com.example.academix.exceptions;

public class CouldntFindFile extends NotFoundException {
    public CouldntFindFile(String message) {
        super(message);
    }
}
