package com.example.academix.repository;

import com.example.academix.model.UserPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    List<UserPost> getAllByEndDate(Date endDate);
    UserPost findByPostId(Long id);
    void deleteUserPostByEndDate(Date endDate);
    @Transactional
    @Modifying
    void deleteUserPostByBeforeEndDate(LocalDateTime date);
}
