package com.example.academix.service;

import com.example.academix.model.Poll;
import com.example.academix.model.PollOption;
import com.example.academix.repository.PollOptionRepository;
import com.example.academix.repository.PollRepository;
import org.springframework.stereotype.Service;
import com.example.academix.util.JwtUtil;
import java.util.List;

@Service
public class PollService {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private final JwtUtil jwtUtil;

    public PollService(PollRepository pollRepository, PollOptionRepository pollOptionRepository, JwtUtil jwtUtil) {
        this.pollRepository = pollRepository;
        this.pollOptionRepository = pollOptionRepository;
        this.jwtUtil = jwtUtil;
    }

    public Poll createPoll(Long groupId, String question, List<String> optionsText) {
        Poll poll = new Poll();
        poll.setGroupId(groupId);
        poll.setQuestion(question);
        
        if (optionsText != null) {
            List<PollOption> options = optionsText.stream().map(text -> {
                PollOption option = new PollOption();
                option.setText(text);
                option.setPoll(poll);
                return option;
            }).toList();
            poll.setOptions(options);
        }
        return pollRepository.save(poll);
    }

    public List<Poll> getPollsByGroup(Long groupId) {
        return pollRepository.findByGroupId(groupId);
    }

    public void vote(Long optionId, String token) {
        Long userId = jwtUtil.extractId(token);
        PollOption option = pollOptionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Option not found"));
        Poll poll = option.getPoll();

        if (poll.getVotedUsers().contains(userId)) {
            throw new RuntimeException("You have already voted on this poll");
        }

        poll.getVotedUsers().add(userId);
        option.setVotes(option.getVotes() + 1);

        pollRepository.save(poll);
        pollOptionRepository.save(option);
    }
}
