package com.example.academix.dto.response;

import com.example.academix.model.AcademiXUser;
import com.example.academix.myEnums.Country;
import com.example.academix.myEnums.GroupAllowance;
import com.example.academix.myEnums.GroupType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
@Data
public class AcademiXGroupResponse {
    private Long id;
    private String groupName;
    private List<AcademiXUser> members;
    private GroupType groupType;
    private String groupPicture;
    private String groupDescription;
    private GroupAllowance groupAllowance;
    private Country country;
    private String groupAdminsName;
    private String groupOwnerName;
}
