package com.mosfik.random.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
/**
 * Tasks Model Class for the database table
 * @author Md. Mosfikur Rahman
 */

@Entity
@Data
@Table(name = "Tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer taskId;
    @Column(name = "TITLE")
    private String taskTitle;
    @Column(name = "DETAILS")
    private String taskDetails;
    @Column(name = "PRIORITY")
    private String priority;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "COMPLETED_AT")
    private Date completedAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_AT")
    private Date modifiedAt;
    @Column(name = "ACTIVE_STATUS")
    private Boolean isActive;
}
