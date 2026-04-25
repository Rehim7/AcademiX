package com.example.academix.model;

import com.example.academix.myEnums.Country;
import com.example.academix.myEnums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AcademiXUser {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private String surName;
    private Roles role;
    private boolean banned;
    private int reportCount;
    private int banTimes = 0;
    private String UUID;
    private Country country;
    private String profilPicture;
    @OneToMany(targetEntity = Note.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(targetEntity = UserPost.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserPost> userPosts;
}
