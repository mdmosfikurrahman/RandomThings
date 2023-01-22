package com.epde.rt.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @Column(name = "title")
    private String taskTitle;
    @Column(name = "details")
    private String taskDetails;
    @Column(name = "priority")
    private String taskPriority;
    @Column(name = "status")
    private String taskStatus;
}
