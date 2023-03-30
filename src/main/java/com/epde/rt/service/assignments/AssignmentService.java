package com.epde.rt.service.assignments;

import com.epde.rt.model.assignments.AssignmentResponse;

public interface AssignmentService {
    AssignmentResponse assignTask(Long taskId, Long userId);
}
