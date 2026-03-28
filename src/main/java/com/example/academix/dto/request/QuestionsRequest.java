package com.example.academix.dto.request;

import com.example.academix.myEnums.Answers;
import com.example.academix.myEnums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionsRequest {
    @NotNull
    private int number;
    @NotNull
    private Answers answer;
    @NotNull
    private Answers correctAnswer;
    @NotBlank(message = "Question needed")
    private String question;
    @NotNull
    private QuestionType questionType;
}
