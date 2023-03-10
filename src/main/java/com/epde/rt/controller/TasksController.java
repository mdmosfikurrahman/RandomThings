package com.epde.rt.controller;

import com.epde.rt.dto.TaskDto;
import com.epde.rt.exception.TaskNotFoundException;
import com.epde.rt.model.tasks.Tasks;
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
    public List<Tasks> getAllTasks(){
        return tasksService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public Tasks getTaskById(@PathVariable Long taskId) {
        return tasksService.getTaskById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tasks createTask(@Valid @RequestBody TaskDto taskDto) {
        Tasks tasks = new Tasks(
                taskDto.getTaskTitle(),
                taskDto.getTaskDetails(),
                taskDto.getTaskPriority(),
                taskDto.getTaskCompleted()
        );
        return tasksService.createTask(tasks);
    }

    @PutMapping("/{taskId}")
    public Tasks updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskDto taskDto) {
        Tasks tasks = new Tasks(
                taskDto.getTaskTitle(),
                taskDto.getTaskDetails(),
                taskDto.getTaskPriority(),
                taskDto.getTaskCompleted()
        );
        return tasksService.updateTask(taskId, tasks)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable Long taskId) {
        tasksService.deleteTaskById(taskId);
    }
}
