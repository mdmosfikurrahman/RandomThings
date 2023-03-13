package com.epde.rt.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Could Not Find Task with ID: " + taskId);
    }

    public TaskNotFoundException(String taskTitle) {
        super("Could Not Find Task with Title: " + taskTitle);
    }
}
