package com.example.academix.repository;

import com.example.academix.model.AcademiXGroup;
import com.example.academix.model.AcademiXUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupRepository extends JpaRepository<AcademiXGroup, Long> {
    List<AcademiXGroup> findByMembersContains(AcademiXUser user);
}
