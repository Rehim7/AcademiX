package com.example.academix.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UserPost {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String file;
    private String description;
    private LocalDateTime created;
    private  int reportCount;
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private AcademiXUser sender;
}