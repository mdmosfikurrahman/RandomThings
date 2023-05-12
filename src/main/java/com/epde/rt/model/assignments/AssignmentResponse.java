package com.epde.rt.model.assignments;

import com.epde.rt.model.tasks.enums.TaskPriority;

public class AssignmentResponse {
    private Long assignmentId;
    private String taskTitle;
    private TaskPriority taskPriority;
    private Boolean taskCompleted;
    private String userFirstName;

    public AssignmentResponse() {
    }

    public AssignmentResponse(Long assignmentId, String taskTitle, TaskPriority taskPriority, Boolean taskCompleted, String userFirstName) {
        this.assignmentId = assignmentId;
        this.taskTitle = taskTitle;
        this.taskPriority = taskPriority;
        this.taskCompleted = taskCompleted;
        this.userFirstName = userFirstName;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Boolean getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(Boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Override
    public String toString() {
        return "AssignmentResponse{" +
                "assignmentId=" + assignmentId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskPriority=" + taskPriority +
                ", taskCompleted=" + taskCompleted +
                ", userFirstName='" + userFirstName + '\'' +
                '}';
    }
}
