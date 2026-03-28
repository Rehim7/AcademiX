package com.example.academix.exceptions;

public class CouldntUploadPost extends FileOperationException {
    public CouldntUploadPost(String message) {
        super(message);
    }
}
