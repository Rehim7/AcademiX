package com.example.academix.controller;

import com.example.academix.dto.request.NoteRequest;
import com.example.academix.dto.response.NoteResponse;
import com.example.academix.model.Note;
import com.example.academix.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest request,@RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok(noteService.createNote(request,accessToken));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNote(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "") String password) {
        return ResponseEntity.ok(noteService.getNote(id, password));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteService.updateNote(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "") String password) {
        return ResponseEntity.ok(noteService.deleteNote(id, password));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(@RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok(noteService.getNotes(accessToken));
    }
}
