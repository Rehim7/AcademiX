package com.example.academix.repository;

import com.example.academix.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
