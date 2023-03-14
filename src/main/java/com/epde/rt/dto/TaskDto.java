package com.epde.rt.dto;

import com.epde.rt.exception.InvalidEnumValueException;
import com.epde.rt.model.tasks.enums.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long taskId;
    @NotBlank(message = "Title is Mandatory!")
    private String taskTitle;
    @NotBlank(message = "Details is Mandatory!")
    private String taskDetails;
    @NotBlank(message = "Priority is Mandatory!")
    private String taskPriority;
    @NotBlank(message = "Completion Status is Mandatory!")
    private Boolean taskCompleted;

    public void validateTaskPriority() {
        try {
            TaskPriority.valueOf(taskPriority);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid task priority: " + taskPriority);
        }
    }
}
