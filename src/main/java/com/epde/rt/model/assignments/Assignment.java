package com.epde.rt.model.assignments;

import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.users.AppUsers;

import jakarta.persistence.*;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUsers user;

    public Assignment(Tasks task, AppUsers user) {
        this.task = task;
        this.user = user;
    }

    public Assignment() {

    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Tasks getTask() {
        return task;
    }

    public void setTask(Tasks task) {
        this.task = task;
    }

    public AppUsers getUser() {
        return user;
    }

    public void setUser(AppUsers user) {
        this.user = user;
    }
}
