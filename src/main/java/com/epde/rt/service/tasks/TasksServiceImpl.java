package com.epde.rt.service.tasks;

import com.epde.rt.exception.tasks.TaskAlreadyExistsException;
import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksServiceImpl implements TasksService {

    private final TasksRepository repository;

    public TasksServiceImpl(TasksRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tasks> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Optional<Tasks> getTaskById(Long taskId) {
        return repository.findById(taskId);
    }

    @Override
    public Optional<Tasks> getTaskByTaskTitle(String taskTitle) {
        return repository.findByTaskTitle(taskTitle);


    }

    @Override
    public Tasks createTask(Tasks tasks) {
        Optional<Tasks> taskTitle = repository.findByTaskTitle(tasks.getTaskTitle());
        if (taskTitle.isPresent()) {
            throw new TaskAlreadyExistsException("Task already exists with title: " + tasks.getTaskTitle());
        }
        repository.save(tasks);
        return tasks;
    }


    @Override
    public Optional<Tasks> updateTask(Long taskId, Tasks tasks) {
        Optional<Tasks> optionalTask = repository.findById(taskId);
        if (optionalTask.isPresent()) {
            tasks.setTaskId(taskId);
            Optional<Tasks> taskTitle = repository.findByTaskTitle(tasks.getTaskTitle());
            if (taskTitle.isPresent()) {
                throw new TaskAlreadyExistsException("Task already exists with title: " + tasks.getTaskTitle());
            } else {
                repository.save(tasks);
            }
        }

        return optionalTask;
    }

    @Override
    public Optional<Tasks> deleteTaskById(Long taskId) {
        Optional<Tasks> optionalTasks = repository.findById(taskId);
        if (optionalTasks.isPresent()) {
            repository.deleteById(taskId);
        }
        return optionalTasks;
    }

    @Override
    public void deleteAllTasks() {
        repository.deleteAll();
    }
}
