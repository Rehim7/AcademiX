package com.example.academix.repository;

import com.example.academix.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByGroupId(Long groupId);
}
