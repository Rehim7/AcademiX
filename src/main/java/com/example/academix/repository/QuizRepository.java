package com.example.academix.repository;

import com.example.academix.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
