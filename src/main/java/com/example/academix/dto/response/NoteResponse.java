package com.example.academix.dto.response;

import lombok.Data;

@Data
public class NoteResponse {
    private Long id;
    private String headLine;
    private String note;
    private String password;
    private String fileUrl;
    private String contentType;
}
