package com.epde.rt.controller;

import com.epde.rt.dto.TaskDto;
import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.service.tasks.TasksServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {
    private final TasksServiceImpl service;

    public TasksController(TasksServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Tasks> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/id-{taskId}")
    public Tasks getTaskById(@PathVariable Long taskId) {
        return service.getTaskById(taskId);
    }

    @GetMapping("/title-{taskTitle}")
    public Tasks getTaskByTitle(@PathVariable String taskTitle) {
        return service.getTaskByTaskTitle(taskTitle);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tasks createTask(@Valid @RequestBody Map<String, Object> taskInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskDto taskDto = objectMapper.convertValue(taskInfo, TaskDto.class);
        Tasks tasks = service.addMethod(taskDto);
        return service.createTask(tasks);
    }

    @PutMapping("/id-{taskId}")
    public Tasks updateTask(@PathVariable Long taskId, @Valid @RequestBody Map<String, Object> taskInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        TaskDto taskDto = objectMapper.convertValue(taskInfo, TaskDto.class);
        Tasks tasks = service.updateMethod(taskId, taskDto);
        return service.updateTask(taskId, tasks);
    }

    @DeleteMapping("/id-{taskId}")
    public List<Tasks> deleteTaskById(@PathVariable Long taskId) {
        return service.deleteTaskById(taskId);
    }

    @DeleteMapping
    public void deleteAllTasks() {
        service.deleteAllTasks();
    }
}