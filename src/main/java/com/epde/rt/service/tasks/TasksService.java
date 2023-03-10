package com.epde.rt.service.tasks;

import com.epde.rt.model.tasks.Tasks;

import java.util.List;
import java.util.Optional;

public interface TasksService {
    List<Tasks> getAllTasks();

    Optional<Tasks> getTaskById(Long taskId);

    Optional<Tasks> getTaskByTaskTitle(String taskTitle);

    void createTask(Tasks tasks);

    Optional<Tasks> updateTask(Long taskId, Tasks tasks);

    Optional<Tasks> deleteTaskById(Long taskId);

    void deleteAllTasks();

}
