package com.example.academix.controller;

import com.example.academix.model.Poll;
import com.example.academix.service.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/group/{groupId}")
    public ResponseEntity<Poll> createPoll(@PathVariable Long groupId, @RequestBody Map<String, Object> payload) {
        String question = (String) payload.get("question");
        List<String> options = (List<String>) payload.get("optionsText");
        return ResponseEntity.ok(pollService.createPoll(groupId, question, options));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Poll>> getPolls(@PathVariable Long groupId) {
        return ResponseEntity.ok(pollService.getPollsByGroup(groupId));
    }

    @PostMapping("/vote/{optionId}")
    public ResponseEntity<Void> vote(@PathVariable Long optionId, @RequestHeader("Authorization") String token) {
        pollService.vote(optionId, token);
        return ResponseEntity.ok().build();
    }
}
