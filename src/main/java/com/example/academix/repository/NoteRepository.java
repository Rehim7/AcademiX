package com.example.academix.repository;

import com.example.academix.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // NoteRepository.java faylında
    List<Note> findAllByUserId(Long userId);
}
