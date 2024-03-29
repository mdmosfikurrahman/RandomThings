package com.epde.rt.service.tasks;

import com.epde.rt.dto.TaskDto;
import com.epde.rt.model.tasks.Tasks;

import java.util.List;

public interface TasksService {
    List<Tasks> getAllTasks();

    Tasks getTaskById(Long taskId);

    Tasks getTaskByTaskTitle(String taskTitle);

    Tasks createTask(Tasks tasks);

    Tasks updateTask(Long taskId, Tasks tasks);

    List<Tasks> deleteTaskById(Long taskId);

    void deleteAllTasks();

    Tasks addMethod(TaskDto taskDto);

    Tasks updateMethod(Long taskId, TaskDto taskDto);
}
