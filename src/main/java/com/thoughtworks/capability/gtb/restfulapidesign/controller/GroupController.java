package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.bo.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:1234")
public class GroupController {
    final private StudentService studentService;
    final private GroupService groupService;

    public GroupController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getGroups() {
        List<Group> groups = groupService.getGroups();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/groups")
    public ResponseEntity<List<Group>> divideGroups() {
        groupService.divideGroups();
        List<Group> groups = groupService.getGroups();
        return ResponseEntity.status(HttpStatus.CREATED).body(groups);
    }

    @PatchMapping("/groups/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable int id, @RequestBody String name) {
        Group updateGroup = groupService.updateGroup(id, name);
        return ResponseEntity.ok().body(updateGroup);
    }

}
