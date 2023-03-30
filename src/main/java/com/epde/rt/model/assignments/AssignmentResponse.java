package com.epde.rt.model.assignments;

import com.epde.rt.model.tasks.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignmentResponse {
    private String taskTitle;
    private TaskPriority taskPriority;
    private Boolean taskCompleted;
    private String userFirstName;
}
