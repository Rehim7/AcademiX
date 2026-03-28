package com.example.academix.service;

import com.example.academix.dto.request.NoteRequest;
import com.example.academix.dto.response.NoteResponse;
import com.example.academix.exceptions.NotFoundException;
import com.example.academix.model.AcademiXUser;
import com.example.academix.model.Note;
import com.example.academix.repository.AcademiXUserRepository;
import com.example.academix.repository.NoteRepository;
import com.example.academix.util.JwtUtil;
import org.checkerframework.checker.units.qual.N;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public NoteResponse createNote(NoteRequest noteRequest) {
        Note note = new Note();
        note.setHeadLine(noteRequest.getHeadLine());
        note.setNote(noteRequest.getNote());
        note.setPassword(noteRequest.getPassword());
        Note save = noteRepository.save(note);
        NoteResponse noteResponse = new NoteResponse();
        noteResponse.setId(save.getId());
        noteResponse.setNote(save.getNote());
        noteResponse.setHeadLine(save.getHeadLine());
        noteResponse.setPassword(save.getPassword());
        return noteResponse;
    }
    public String  deleteNote(Long id,String password) {
        Optional<Note> byId = noteRepository.findById(id);
        if (byId.get().getPassword().equals(password)) {
            noteRepository.deleteById(id);
            return "Note has been deleted";
        }
        return "Wrong password or id";
    }
    public NoteResponse updateNote(Long id,NoteRequest noteRequest) {
        Optional<Note> byId = noteRepository.findById(id);
        NoteResponse noteResponse = new NoteResponse();
        if (byId.get().getPassword().equals(noteRequest.getPassword())) {
            byId.get().setHeadLine(noteRequest.getHeadLine());
            byId.get().setNote(noteRequest.getNote());
            byId.get().setPassword(noteRequest.getPassword());
            Note save = noteRepository.save(byId.get());
            noteResponse.setId( save.getId());
            noteResponse.setNote(save.getNote());
            noteResponse.setPassword(save.getPassword());
            noteResponse.setHeadLine(save.getHeadLine());
            return noteResponse;
        }
        throw new NotFoundException("Wrong password");
    }
    public  NoteResponse getNote(Long id,String password) {
        Optional<Note> byId = noteRepository.findById(id);
        NoteResponse noteResponse = new NoteResponse();
        if (!byId.get().getPassword().equals(password)) {
            throw new NotFoundException("Wrong password");
        }
        noteResponse.setId(byId.get().getId());
        noteResponse.setNote(byId.get().getNote());
        noteResponse.setPassword(byId.get().getPassword());
        noteResponse.setHeadLine(byId.get().getHeadLine());
        return noteResponse;
    }
    public List<Note> getNotes(String accessToken) {
        Long l = jwtUtil.extractId(accessToken);
        Optional<AcademiXUser> byId = academiXUserRepository.findById(l);
        NoteResponse noteResponse = new NoteResponse();
        return byId.get().getNotes();
    }
}
