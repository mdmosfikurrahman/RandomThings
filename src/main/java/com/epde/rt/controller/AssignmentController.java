package com.epde.rt.controller;

import com.epde.rt.model.assignments.Assignment;
import com.epde.rt.model.assignments.AssignmentRequest;
import com.epde.rt.model.assignments.AssignmentResponse;
import com.epde.rt.service.assignments.AssignmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/assignments")
public class AssignmentController {
    private final AssignmentService service;

    public AssignmentController(AssignmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return service.getAllAssignments();
    }

    @GetMapping("/id-{assignmentId}")
    public AssignmentResponse getAssignmentById(@PathVariable Long assignmentId) {
        return service.getAssignmentById(assignmentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssignmentResponse assignTask(@Valid @RequestBody Map<String, Object> assignmentInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        AssignmentRequest assignmentRequest = objectMapper.convertValue(assignmentInfo, AssignmentRequest.class);
        return service.assignTask(assignmentRequest.getTaskId(), assignmentRequest.getUserId());
    }

    @DeleteMapping("/id-{assignmentId}")
    public List<Assignment> deleteAssignmentById(@PathVariable Long assignmentId) {
        return service.deleteAssignmentById(assignmentId);
    }

    @DeleteMapping
    public void deleteAllAssignments() {
        service.deleteAllAssignments();
    }

}