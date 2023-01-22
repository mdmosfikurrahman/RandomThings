package com.epde.rt.model.tasks;

import com.epde.rt.model.tasks.enums.TaskPriority;
import com.epde.rt.model.tasks.enums.TaskStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    private String taskTitle;
    private String taskDetails;
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date taskCreateDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date taskCompleteDate;
}
