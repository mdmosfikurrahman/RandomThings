package com.epde.rt.model.assignments;

import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.users.AppUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
