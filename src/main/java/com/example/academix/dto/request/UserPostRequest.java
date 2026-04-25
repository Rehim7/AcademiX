package com.example.academix.dto.request;

import com.example.academix.model.AcademiXUser;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPostRequest {
    @NotBlank(message = "Title needed")
    private String title;
    private String file;
    private String description;
    @NotNull
    private LocalDateTime endDate;
}
