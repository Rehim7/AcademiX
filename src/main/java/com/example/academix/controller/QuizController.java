package com.example.academix.controller;

import com.example.academix.dto.request.QuizRequest;
import com.example.academix.model.Quiz;
import com.example.academix.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody QuizRequest request) {
        return ResponseEntity.ok(quizService.createQuiz(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }

    @GetMapping
    public ResponseEntity<java.util.List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<com.example.academix.dto.response.QuizResultResponse> submitQuiz(@PathVariable Long id, @RequestBody java.util.Map<String, Object> requestBody) {
        java.util.Map<String, String> answers = (java.util.Map<String, String>) requestBody.get("answers");
        return ResponseEntity.ok(quizService.submitQuiz(id, answers));
    }
}
