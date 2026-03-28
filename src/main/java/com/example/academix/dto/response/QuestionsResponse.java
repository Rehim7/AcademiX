package com.example.academix.dto.response;

import com.example.academix.myEnums.Answers;
import com.example.academix.myEnums.QuestionType;
import lombok.Data;

@Data
public class QuestionsResponse {
    private int number;
    private Answers answer;
    private String question;
    private QuestionType questionType;
}
