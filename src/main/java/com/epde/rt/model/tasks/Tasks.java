package com.epde.rt.model.tasks;

import com.epde.rt.model.tasks.enums.TaskPriority;
import com.epde.rt.model.tasks.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    @NonNull
    private String taskTitle;
    @NonNull
    private String taskDetails;
    @NonNull
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskCreateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskCompleteDate;


    public Tasks() {

    }
}
