package com.example.academix.repository;

import com.example.academix.model.AcademiXUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademiXUserRepository extends JpaRepository<AcademiXUser, Long> {
    List<AcademiXUser> findByEmail(String email);
    AcademiXUser findByUserId(Long id);
}
