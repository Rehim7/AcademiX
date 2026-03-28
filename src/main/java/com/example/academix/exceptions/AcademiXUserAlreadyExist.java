package com.example.academix.exceptions;

public class AcademiXUserAlreadyExist extends AlreadyExistsException {
    public AcademiXUserAlreadyExist(String message) {
        super(message);
    }
}
