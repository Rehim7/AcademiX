package com.example.academix.controller;

import com.example.academix.dto.request.AcademiXGroupRequest;
import com.example.academix.dto.response.AcademiXGroupResponse;
import com.example.academix.service.AcademiXGroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class AcademiXGroupController {

    private final AcademiXGroupService groupService;

    public AcademiXGroupController(AcademiXGroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public ResponseEntity<AcademiXGroupResponse> createGroup(
            @Valid @RequestBody AcademiXGroupRequest request,
            @RequestHeader("Authorization") String accessToken) {
        AcademiXGroupResponse response = groupService.createGroup(request, accessToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<String> joinGroup(
            @PathVariable Long id,
            @RequestParam String password,
            @RequestHeader("Authorization") String accessToken) {
        groupService.joinGroup(id, password, accessToken);
        return ResponseEntity.ok("Successfully joined the group");
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<?> getGroupMembers(@PathVariable Long id) {
        // Accessing Group Service to get members without heavy abstraction 
        // We'll rely on the existing AcademiXGroupService or simply inject GroupRepository if we had it.
        // Let's add it to the GroupService directly.
        return ResponseEntity.ok(groupService.getGroupMembers(id));
    }
    @GetMapping("/my")
    public ResponseEntity<java.util.List<AcademiXGroupResponse>> getMyGroups(
            @RequestHeader("Authorization") String accessToken) {
        return ResponseEntity.ok(groupService.getMyGroups(accessToken));
    }

    @GetMapping("/all")
    public ResponseEntity<java.util.List<AcademiXGroupResponse>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(
            @PathVariable Long id,
            @RequestHeader("Authorization") String accessToken) {
        groupService.deleteGroup(id, accessToken);
        return ResponseEntity.ok("Group deleted successfully");
    }
}
