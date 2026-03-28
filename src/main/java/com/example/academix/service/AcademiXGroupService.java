package com.example.academix.service;

import com.example.academix.dto.request.AcademiXGroupRequest;
import com.example.academix.dto.response.AcademiXGroupResponse;
import com.example.academix.exceptions.NotFoundException;
import com.example.academix.model.AcademiXGroup;
import com.example.academix.model.AcademiXUser;
import com.example.academix.myEnums.Country;
import com.example.academix.myEnums.GroupAllowance;
import com.example.academix.myEnums.GroupType;
import com.example.academix.repository.AcademiXUserRepository;
import com.example.academix.repository.GroupRepository;
import com.example.academix.util.JwtUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademiXGroupService {
    private final GroupRepository groupRepository;
    private final JwtUtil jwtUtil;
    private final AcademiXUserRepository userRepository;
    public AcademiXGroupService(GroupRepository groupRepository, JwtUtil jwtUtil, AcademiXUserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public AcademiXGroupResponse createGroup(AcademiXGroupRequest academiXGroupRequest, String accessToken) {
        Long l = jwtUtil.extractId(accessToken);
        AcademiXUser byUserId = userRepository.findByUserId(l);
        AcademiXGroup academiXGroup = new AcademiXGroup();
        academiXGroup.setGroupName(academiXGroupRequest.getGroupName());
        academiXGroup.setGroupDescription(academiXGroupRequest.getGroupDescription());
        academiXGroup.setGroupType(academiXGroupRequest.getGroupType());
        academiXGroup.setCountry(academiXGroupRequest.getCountry());
        academiXGroup.setGroupPicture(academiXGroupRequest.getGroupPicture());
        academiXGroup.setGroupAllowance(academiXGroupRequest.getGroupAllowance());
        academiXGroup.setPassword(academiXGroupRequest.getPassword());
        academiXGroup.setGroupOwner(byUserId);
        AcademiXGroup save = groupRepository.save(academiXGroup);
        AcademiXGroupResponse academiXGroupResponse = getAcademiXGroupResponse(save);
        return academiXGroupResponse;
    }

    private static AcademiXGroupResponse getAcademiXGroupResponse(AcademiXGroup save) {
        AcademiXGroupResponse academiXGroupResponse = new AcademiXGroupResponse();
        academiXGroupResponse.setCountry(save.getCountry());
        academiXGroupResponse.setGroupName(save.getGroupName());
        academiXGroupResponse.setGroupDescription(save.getGroupDescription());
        academiXGroupResponse.setGroupType(save.getGroupType());
        academiXGroupResponse.setGroupPicture(save.getGroupPicture());
        academiXGroupResponse.setGroupAllowance(save.getGroupAllowance());
        academiXGroupResponse.setMembers(save.getMembers());
        academiXGroupResponse.setGroupAdminsName(save.getGroupAdmins().getName());
        academiXGroupResponse.setGroupOwnerName(save.getGroupOwner().getName());
        academiXGroupResponse.setId(save.getId());
        return academiXGroupResponse;
    }

    public void joinGroup(Long id,String password,String accessToken){
        AcademiXGroup byGroupId = groupRepository.findByGroupId(id);
        Long l = jwtUtil.extractId(accessToken);
        AcademiXUser byUserId = userRepository.findByUserId(l);
        if (byGroupId == null) {
            throw new NotFoundException("Group not found");
        }
        if (byGroupId.getPassword().equals(password)) {
            List<AcademiXUser> members = byGroupId.getMembers();
            members.add(byUserId);
            groupRepository.save(byGroupId);
        }
        
    }

}
