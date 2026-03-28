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
    private String groupName;
    @NotNull(message = "Group type required ")
    private GroupType groupType;
    private String groupPicture;
    private String groupDescription;
    @NotNull(message = "Group allowance cant be empty")
    private GroupAllowance groupAllowance;
    private String password;
    @NotNull(message = "Country needed for availability to other users")
    private Country country;
}
