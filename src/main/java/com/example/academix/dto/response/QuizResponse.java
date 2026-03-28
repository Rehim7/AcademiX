package com.example.academix.dto.response;

import com.example.academix.model.Questions;
import com.example.academix.myEnums.QuizByClasses;
import lombok.Data;

import java.util.List;

@Data
public class QuizResponse {
    private String title;
    private String questions;
    private QuizByClasses quizByClasses;
}
