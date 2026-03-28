package com.example.academix.model;

import com.example.academix.myEnums.Country;
import com.example.academix.myEnums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
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
    private int banTimes;
    private String UUID;
    private Country country;
    private String profilPicture;
    @OneToMany(targetEntity = Note.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(targetEntity = UserPost.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserPost> userPosts;
}
