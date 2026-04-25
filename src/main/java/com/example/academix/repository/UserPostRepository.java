package com.example.academix.repository;

import com.example.academix.model.UserPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDateTime;
import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> findAllByEndDate(LocalDateTime endDate);
    
    @Transactional
    @Modifying
    void deleteByEndDate(LocalDateTime endDate);
    
    @Transactional
    @Modifying
    void deleteByEndDateBefore(LocalDateTime date);
}
