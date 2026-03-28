package com.example.academix.dto.request;

import com.example.academix.model.Questions;
import com.example.academix.myEnums.QuizByClasses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class QuizRequest {
    @NotBlank(message = "Quiz needs a name")
    private String title;
    @NotNull(message = "Quiz needs at least 1 question")
    private Long questionsId;
    @NotNull
    private QuizByClasses quizByClasses;
}
