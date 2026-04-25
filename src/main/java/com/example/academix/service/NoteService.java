package com.example.academix.service;

import com.example.academix.dto.request.NoteRequest;
import com.example.academix.dto.response.NoteResponse;
import com.example.academix.exceptions.NotFoundException;
import com.example.academix.model.AcademiXUser;
import com.example.academix.model.Note;
import com.example.academix.repository.AcademiXUserRepository;
import com.example.academix.repository.NoteRepository;
import com.example.academix.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final AcademiXUserRepository academiXUserRepository;
    private final NoteRepository noteRepository;
    private final JwtUtil jwtUtil;
    public NoteService(AcademiXUserRepository academiXUserRepository, NoteRepository noteRepository, JwtUtil jwtUtil) {
        this.academiXUserRepository = academiXUserRepository;
        this.noteRepository = noteRepository;
        this.jwtUtil = jwtUtil;
    }
    public NoteResponse createNote(NoteRequest noteRequest, String accessToken) {
        Long userId = jwtUtil.extractId(accessToken);

        // İstifadəçinin mövcudluğunu yoxlayırıq
        if (!academiXUserRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }

        Note note = new Note();
        note.setHeadLine(noteRequest.getHeadLine());
        note.setNote(noteRequest.getNote());
        note.setPassword(noteRequest.getPassword());
        note.setFileUrl(noteRequest.getFileUrl());
        note.setContentType(noteRequest.getContentType());

        // BURADA: Əgər Note modelində 'userId' sahəsi Long-dursa:
        note.setUserId(userId);

        // ƏGƏR Note modelində 'user' sahəsi AcademiXUser-dirsə, belə edin:
        // AcademiXUser user = academiXUserRepository.findById(userId).get();
        // note.setUser(user);

        Note savedNote = noteRepository.save(note);
        return mapToResponse(savedNote);
    }

    public List<Note> getNotes(String accessToken) {
        Long userId = jwtUtil.extractId(accessToken);

        // user.getNotes() yerinə REPO-dan birbaşa sorğu atırıq
        return noteRepository.findAllByUserId(userId);
    }

    public String deleteNote(Long id, String password) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note not found with id: " + id));
        if (note.getPassword().equals(password)) {
            noteRepository.deleteById(id);
            return "Note has been deleted";
        }
        throw new NotFoundException("Wrong password");
    }

    public NoteResponse updateNote(Long id, NoteRequest noteRequest) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note not found with id: " + id));
        if (!note.getPassword().equals(noteRequest.getPassword())) {
            throw new NotFoundException("Wrong password");
        }
        note.setHeadLine(noteRequest.getHeadLine());
        note.setNote(noteRequest.getNote());
        note.setPassword(noteRequest.getPassword());
        note.setFileUrl(noteRequest.getFileUrl());
        note.setContentType(noteRequest.getContentType());
        Note save = noteRepository.save(note);
        return mapToResponse(save);
    }

    public NoteResponse getNote(Long id, String password) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note not found with id: " + id));
        if (!note.getPassword().equals(password)) {
            throw new NotFoundException("Wrong password");
        }
        return mapToResponse(note);
    }


    private NoteResponse mapToResponse(Note note) {
        NoteResponse noteResponse = new NoteResponse();
        noteResponse.setId(note.getId());
        noteResponse.setNote(note.getNote());
        noteResponse.setHeadLine(note.getHeadLine());
        noteResponse.setPassword(note.getPassword());
        noteResponse.setFileUrl(note.getFileUrl());
        noteResponse.setContentType(note.getContentType());
        return noteResponse;
    }
}
