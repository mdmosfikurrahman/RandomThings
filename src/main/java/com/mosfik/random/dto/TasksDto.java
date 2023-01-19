package com.mosfik.random.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Data Transfer Object for Tasks
 * @author Md. Mosfikur Rahman
 */

@Data
public class TasksDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer taskId;
    private String taskTitle;
    private String taskDetails;
    private String priority;
    private Boolean isActive;

}
