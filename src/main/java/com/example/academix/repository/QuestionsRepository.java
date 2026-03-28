package com.example.academix.repository;

import com.example.academix.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByNumber(int number);
}
