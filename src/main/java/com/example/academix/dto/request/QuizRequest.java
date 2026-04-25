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
    private Integer timeLimitSeconds;
    private java.util.List<QuestionDto> questions;
    
    @Data
    public static class QuestionDto {
        private Long id;
        private String text;
        private Long correctOptionId;
        private java.util.List<OptionDto> options;
    }

    @Data
    public static class OptionDto {
        private Long id;
        private String text;
    }
}
