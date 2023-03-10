package com.epde.rt.service.tasks;

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
    public Tasks createTask(Tasks tasks) {
        return repository.save(tasks);
    }

    @Override
    public Optional<Tasks> updateTask(Long taskId, Tasks tasks) {
        Optional<Tasks> optionalTask = repository.findById(taskId);
        if (optionalTask.isPresent()) {
            tasks.setTaskId(taskId);
            repository.save(tasks);
        }
        return optionalTask;
    }

    @Override
    public void deleteTaskById(Long taskId) {
        repository.deleteById(taskId);
    }
}