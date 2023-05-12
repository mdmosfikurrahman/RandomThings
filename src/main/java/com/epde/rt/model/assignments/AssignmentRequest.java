package com.epde.rt.model.assignments;


public class AssignmentRequest {
    private Long taskId;
    private Long userId;

    public AssignmentRequest(Long taskId, Long userId) {
        this.taskId = taskId;
        this.userId = userId;
    }

    public AssignmentRequest() {
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
