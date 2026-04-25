package com.example.academix.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoteRequest {
    @NotBlank(message = "Headline cant be empty")
    private String headLine;
    @NotBlank(message = "Note needed")
    private String note;
    @NotBlank(message = "Password needed for security")
    private String password;
    private String fileUrl;
    private String contentType;
}
