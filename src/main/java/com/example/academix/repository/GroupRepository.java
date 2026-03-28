package com.example.academix.repository;

import com.example.academix.model.AcademiXGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<AcademiXGroup, Long> {
    AcademiXGroup findByGroupId(Long groupId);
}
