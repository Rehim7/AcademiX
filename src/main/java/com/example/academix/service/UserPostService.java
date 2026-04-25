package com.example.academix.service;

import com.example.academix.dto.request.UserPostRequest;
import com.example.academix.dto.response.UserPostResponse;
import com.example.academix.model.AcademiXUser;
import com.example.academix.model.UserPost;
import com.example.academix.repository.AcademiXUserRepository;
import com.example.academix.repository.UserPostRepository;
import com.example.academix.util.JwtUtil;
import com.google.type.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserPostService {
    private final UserPostRepository userPostRepository;
    private final AcademiXUserRepository  academiXUserRepository;
    private final JwtUtil jwtUtil;
    public UserPostService(UserPostRepository userPostRepository, AcademiXUserRepository academiXUserRepository, JwtUtil jwtUtil) {
        this.userPostRepository = userPostRepository;
        this.academiXUserRepository = academiXUserRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserPostResponse createUserPost(UserPostRequest userPostRequest,String accessToken) {
        AcademiXUser byId = academiXUserRepository.findById(jwtUtil.extractId(accessToken)).orElseThrow(() -> new RuntimeException("User not found"));
        UserPost userPost = new UserPost();
        userPost.setCreated(LocalDateTime.now());
        userPost.setFile(userPostRequest.getFile());
        userPost.setTitle(userPostRequest.getTitle());
        userPost.setDescription(userPostRequest.getDescription());
        userPost.setSender(byId);
        userPost.setReportCount(0);
        userPost.setEndDate(userPostRequest.getEndDate());
        userPostRepository.save(userPost);
        return getUserPostResponse(userPost);
    }
    public void deletePost(Long id){
        userPostRepository.deleteById(id);
    }

    @Scheduled(cron = "0 * * * * *")
    public void deletePostAuto(){
        userPostRepository.deleteByEndDateBefore(LocalDateTime.now());
    }
    public UserPostResponse getPost(Long id) {
        UserPost userPost = userPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return getUserPostResponse(userPost);
    }
    public List<UserPost> getPostsByUser(Long userId) {
        AcademiXUser byUserId = academiXUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserPost> list = byUserId.getUserPosts().stream().toList();
        return list;
    }

    public List<UserPostResponse> getAllPosts() {
        return userPostRepository.findAll().stream()
                .map(this::getUserPostResponse)
                .toList();
    }

    private UserPostResponse getUserPostResponse(UserPost userPost) {
        UserPostResponse userPostResponse = new UserPostResponse();
        userPostResponse.setId(userPost.getId());
        userPostResponse.setCreated(userPost.getCreated());
        userPostResponse.setFile(userPost.getFile());
        userPostResponse.setTitle(userPost.getTitle());
        userPostResponse.setDescription(userPost.getDescription());
        userPostResponse.setSenderName(userPost.getSender().getName());
        userPostResponse.setEndDate(userPost.getEndDate());
        return userPostResponse;
    }


}
