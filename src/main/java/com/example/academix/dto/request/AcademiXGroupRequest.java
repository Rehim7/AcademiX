package com.example.academix.dto.request;

import com.example.academix.model.AcademiXUser;
import com.example.academix.myEnums.Country;
import com.example.academix.myEnums.GroupAllowance;
import com.example.academix.myEnums.GroupType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class AcademiXGroupRequest {
    @NotBlank(message = "Group needs a name")
    private String name; // mapped from frontend groupName -> name
    private GroupType groupType;
    private String groupPicture;
    private String groupDescription;
    private GroupAllowance groupAllowance;
    private String password;
    private Country country;
}
