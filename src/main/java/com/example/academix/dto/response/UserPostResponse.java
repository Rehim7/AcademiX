package com.example.academix.dto.response;

import com.example.academix.model.AcademiXUser;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class UserPostResponse {
    private String title;
    private String file;
    private String description;
    private LocalDateTime created;
    private Date endDate;
    private String senderName;
}
