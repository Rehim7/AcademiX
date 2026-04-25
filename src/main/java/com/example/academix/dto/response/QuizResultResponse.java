package com.example.academix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultResponse {
    private int totalQuestions;
    private int correctAnswers;
    private int wrongAnswers;
    private int unanswered;
    private double percentage;
}
