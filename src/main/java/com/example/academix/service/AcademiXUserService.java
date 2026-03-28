package com.example.academix.service;

import com.example.academix.dto.response.AcademiXUserResponse;
import com.example.academix.exceptions.AcademiXUserAlreadyExist;
import com.example.academix.model.AcademiXUser;
import com.example.academix.myEnums.Roles;
import com.example.academix.repository.AcademiXUserRepository;
import com.example.academix.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AcademiXUserService {
    private final JwtUtil jwtUtil;
    private final AcademiXUserRepository academiXUserRepository;
    public AcademiXUserService(JwtUtil jwtUtil, AcademiXUserRepository academiXUserRepository) {
        this.jwtUtil = jwtUtil;
        this.academiXUserRepository = academiXUserRepository;
    }
    public String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    public AcademiXUserResponse mapToResponse(AcademiXUser academiXUser){
        AcademiXUserResponse academiXUserResponse = new AcademiXUserResponse();
        academiXUserResponse.setBanned(academiXUser.isBanned());
        academiXUserResponse.setCountry(academiXUser.getCountry());
        academiXUserResponse.setProfilPicture(academiXUser.getProfilPicture());
        academiXUserResponse.setUUID(academiXUser.getUUID());
        academiXUserResponse.setBanTimes(academiXUser.getBanTimes());
        academiXUserResponse.setRole(academiXUser.getRole());
        academiXUserResponse.setName(academiXUser.getName());
        academiXUserResponse.setSurName(academiXUser.getSurName());
        academiXUserResponse.setEmail(academiXUser.getEmail());
        academiXUserResponse.setPassword(academiXUser.getPassword());
        return academiXUserResponse;
    }

    public AcademiXUserResponse createUser(String name, String surName, String email, String password, Roles role,Long id){
        academiXUserRepository.findById(id).ifPresent(academiXUser -> { throw new AcademiXUserAlreadyExist("AcademiX user already exist");
        });
        List<AcademiXUser> byEmail = academiXUserRepository.findByEmail(email);
        if (!byEmail.isEmpty()) {
            throw new AcademiXUserAlreadyExist("AcademiX user already exist with this email");
        }
        AcademiXUser academiXUser = new AcademiXUser();
        academiXUser.setName(name);
        academiXUser.setSurName(surName);
        academiXUser.setEmail(email);
        academiXUser.setPassword(password);
        academiXUser.setRole(role);
        academiXUser.setId(id);
        academiXUser.setBanned(false);
        academiXUser.setBanTimes(0);
        academiXUser.setUUID(generateRandomId());
        return mapToResponse(academiXUser);
    }

}
