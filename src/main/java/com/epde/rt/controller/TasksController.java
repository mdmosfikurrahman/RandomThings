package com.epde.rt.controller;

import com.epde.rt.dto.TaskDto;
import com.epde.rt.exception.tasks.TaskNotFoundException;
import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.tasks.enums.TaskPriority;
import com.epde.rt.service.tasks.TasksServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {
    private final TasksServiceImpl tasksService;

    public TasksController(TasksServiceImpl tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public List<Tasks> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @GetMapping("/id-{taskId}")
    public Tasks getTaskById(@PathVariable Long taskId) {
        return tasksService.getTaskById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @GetMapping("/title-{taskTitle}")
    public Tasks getTaskByTitle(@PathVariable String taskTitle) {
        return tasksService.getTaskByTaskTitle(taskTitle).orElseThrow(() -> new TaskNotFoundException(taskTitle));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@Valid @RequestBody TaskDto taskDto) {
        taskDto.validateTaskPriority();
        TaskPriority taskPriority = TaskPriority.valueOf(taskDto.getTaskPriority());

        Tasks tasks = new Tasks(
                taskDto.getTaskTitle(),
                taskDto.getTaskDetails(),
                taskPriority,
                taskDto.getTaskCompleted()
        );
        tasksService.createTask(tasks);
    }

    @PutMapping("/id-{taskId}")
    public Tasks updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskDto taskDto) {
        taskDto.validateTaskPriority();
        TaskPriority taskPriority = TaskPriority.valueOf(taskDto.getTaskPriority());

        Tasks tasks = new Tasks(
                taskDto.getTaskTitle(),
                taskDto.getTaskDetails(),
                taskPriority,
                taskDto.getTaskCompleted()
        );
        return tasksService.updateTask(taskId, tasks).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @DeleteMapping("/id-{taskId}")
    public Tasks deleteTaskById(@PathVariable Long taskId) {
        return tasksService.deleteTaskById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @DeleteMapping
    public void deleteAllTasks() {
        tasksService.deleteAllTasks();
    }
}