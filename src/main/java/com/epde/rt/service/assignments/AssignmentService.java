package com.epde.rt.service.assignments;

import com.epde.rt.model.assignments.Assignment;

public interface AssignmentService {
    Assignment assignTask(Long taskId, Long userId);
}
