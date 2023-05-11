package com.epde.rt.dto;

import com.epde.rt.exception.InvalidEnumValueException;
import com.epde.rt.model.tasks.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long taskId;
    private String taskTitle;
    private String taskDetails;
    private String taskPriority;
    private Boolean taskCompleted;

    public void validateTaskPriority() {
        try {
            TaskPriority.valueOf(taskPriority);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid task priority: " + taskPriority);
        }
    }
}
