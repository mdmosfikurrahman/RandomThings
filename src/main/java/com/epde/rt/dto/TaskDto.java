package com.epde.rt.dto;

import com.epde.rt.exception.InvalidEnumValueException;
import com.epde.rt.model.tasks.enums.TaskPriority;

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

    public TaskDto(Long taskId, String taskTitle, String taskDetails, String taskPriority, Boolean taskCompleted) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskPriority = taskPriority;
        this.taskCompleted = taskCompleted;
    }

    public TaskDto() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Boolean getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(Boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDetails='" + taskDetails + '\'' +
                ", taskPriority='" + taskPriority + '\'' +
                ", taskCompleted=" + taskCompleted +
                '}';
    }
}
