package com.example.academix.model;

import com.example.academix.myEnums.QuizByClasses;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private List<Questions> questions;
    private QuizByClasses quizByClasses;
    private Integer timeLimitSeconds;
}
