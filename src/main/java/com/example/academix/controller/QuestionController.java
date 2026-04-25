package com.example.academix.controller;

import com.example.academix.dto.request.QuestionsRequest;
import com.example.academix.dto.response.QuestionsResponse;
import com.example.academix.model.Questions;
import com.example.academix.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Questions>> findAll() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @PostMapping
    public ResponseEntity<QuestionsResponse> createQuestion(@Valid @RequestBody QuestionsRequest request) {
        return ResponseEntity.ok(questionService.createQuestion(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
