package com.epde.rt.service.assignments;

import com.epde.rt.model.assignments.Assignment;
import com.epde.rt.model.assignments.AssignmentResponse;

import java.util.List;

public interface AssignmentService {
    List<Assignment> getAllAssignments();
    AssignmentResponse getAssignmentById(Long assignmentId);
    AssignmentResponse assignTask(Long taskId, Long userId);
    List<Assignment> deleteAssignmentById(Long assignmentId);
    void deleteAllAssignments();

}
