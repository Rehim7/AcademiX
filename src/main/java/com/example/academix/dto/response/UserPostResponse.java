package com.example.academix.dto.response;

import com.example.academix.model.AcademiXUser;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserPostResponse {
    private String title;
    private String file;
    private String description;
    private LocalDateTime created;
    private LocalDateTime endDate;
    private String senderName;
}
