package com.epde.rt.model.tasks;

import com.epde.rt.model.tasks.enums.TaskPriority;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column(nullable = false)
    private String taskTitle;
    @Column(nullable = false)
    private String taskDetails;
    @Column(nullable = false)
    private TaskPriority taskPriority;
    @Column(nullable = false)
    private Boolean taskCompleted;

    public Tasks(String taskTitle, String taskDetails, TaskPriority taskPriority, Boolean taskCompleted) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.taskPriority = taskPriority;
        this.taskCompleted = taskCompleted;
    }
}

