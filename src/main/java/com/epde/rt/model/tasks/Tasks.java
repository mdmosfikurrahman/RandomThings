package com.epde.rt.model.tasks;

import com.epde.rt.model.tasks.enums.TaskPriority;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String taskTitle;

    private String taskDetails;

    private TaskPriority taskPriority;

    private Boolean taskCompleted;

    public Tasks(String taskTitle, String taskDetails, TaskPriority taskPriority, Boolean taskCompleted) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskPriority = taskPriority;
        this.taskCompleted = taskCompleted;
    }

    public Tasks() {
    }

    public Tasks(Long taskId, String taskTitle, String taskDetails, TaskPriority taskPriority, Boolean taskCompleted) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskPriority = taskPriority;
        this.taskCompleted = taskCompleted;
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

    @Override
    public String toString() {
        return "Tasks{" +
                "taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDetails='" + taskDetails + '\'' +
                ", taskPriority=" + taskPriority +
                ", taskCompleted=" + taskCompleted +
                '}';
    }
}