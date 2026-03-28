package com.example.academix.model;

import com.example.academix.myEnums.Answers;
import com.example.academix.myEnums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private Answers answer;
    private boolean correct;
    private String question;
    private Answers correctAnswer;
    private QuestionType questionType;
}
