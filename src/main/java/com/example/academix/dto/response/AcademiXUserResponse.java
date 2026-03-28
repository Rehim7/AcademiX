package com.example.academix.dto.response;

import com.example.academix.myEnums.Country;
import com.example.academix.myEnums.Roles;
import lombok.Data;

@Data
public class AcademiXUserResponse {
    private String name;
    private String email;
    private String password;
    private String surName;
    private Roles role;
    private boolean banned;
    private int banTimes;
    private String UUID;
    private Country country;
    private String profilPicture;
}
