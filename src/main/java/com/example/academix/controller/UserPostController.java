package com.example.academix.controller;

import com.example.academix.dto.request.UserPostRequest;
import com.example.academix.dto.response.UserPostResponse;
import com.example.academix.model.UserPost;
import com.example.academix.service.UserPostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class UserPostController {

    private final UserPostService userPostService;

    public UserPostController(UserPostService userPostService) {
        this.userPostService = userPostService;
    }

    @PostMapping
    public ResponseEntity<UserPostResponse> createPost(
            @Valid @RequestBody UserPostRequest request,
            @RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok(userPostService.createUserPost(request, accessToken));
    }

    @GetMapping
    public ResponseEntity<List<UserPostResponse>> getAllPosts() {
        return ResponseEntity.ok(userPostService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(userPostService.getPost(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPost>> getPostsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userPostService.getPostsByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        userPostService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
