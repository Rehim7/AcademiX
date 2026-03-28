package com.example.academix.model;

import com.example.academix.myEnums.Country;
import com.example.academix.myEnums.GroupAllowance;
import com.example.academix.myEnums.GroupType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AcademiXGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_members", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<AcademiXUser> members;
    private GroupType groupType;
    private String groupPicture;
    private String groupDescription;
    private GroupAllowance groupAllowance;
    private String password;
    private Country country;
    @ManyToOne(fetch = FetchType.LAZY)
    private AcademiXUser groupAdmins;
    @ManyToOne(fetch = FetchType.LAZY)
    private AcademiXUser groupOwner;
}
