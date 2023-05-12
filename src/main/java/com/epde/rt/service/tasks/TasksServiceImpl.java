package com.epde.rt.service.tasks;

import com.epde.rt.dto.TaskDto;
import com.epde.rt.exception.ResourceAlreadyExistsException;
import com.epde.rt.exception.ResourceNotFoundException;
import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.tasks.enums.TaskPriority;
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

    /**
     * @return tasksList
     */
    @Override
    public List<Tasks> getAllTasks() {
        List<Tasks> tasksList;
        if (repository.count() != 0) {
            tasksList = repository.findAll();
        } else {
            throw new ResourceNotFoundException("No Tasks Found!");
        }
        return tasksList;
    }

    @Override
    public Tasks getTaskById(Long taskId) {
        return repository.findById(taskId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Task not found with ID: " + taskId);
        });
    }

    @Override
    public Tasks getTaskByTaskTitle(String taskTitle) {
        return repository.findByTaskTitle(taskTitle).orElseThrow(() -> {
            throw new ResourceNotFoundException("Task not found with Title: " + taskTitle);
        });
    }

    @Override
    public Tasks createTask(Tasks tasks) {
        Optional<Tasks> taskTitle = repository.findByTaskTitle(tasks.getTaskTitle());
        if (taskTitle.isPresent()) {
            throw new ResourceAlreadyExistsException("Task already exists with Title: " + tasks.getTaskTitle());
        }
        repository.save(tasks);
        return tasks;
    }

    public Tasks addMethod(TaskDto taskDto) {
        taskDto.validateTaskPriority();
        TaskPriority taskPriority = TaskPriority.valueOf(taskDto.getTaskPriority());

        return new Tasks(taskDto.getTaskTitle(), taskDto.getTaskDetails(), taskPriority, taskDto.getTaskCompleted());
    }

    @Override
    public Tasks updateTask(Long taskId, Tasks tasks) {
        Optional<Tasks> optionalTask = repository.findById(taskId);
        if (optionalTask.isPresent()) {
            tasks.setTaskId(taskId);
            Optional<Tasks> taskTitle = repository.findByTaskTitle(tasks.getTaskTitle());
            if (taskTitle.isPresent()) {
                throw new ResourceAlreadyExistsException("Task already exists with Title: " + tasks.getTaskTitle());
            } else {
                repository.save(tasks);
            }
        } else {
            throw new ResourceNotFoundException("Task not found with ID: " + taskId);
        }

        return tasks;
    }

    @Override
    public Tasks updateMethod(Long taskId, TaskDto taskDto) {
        taskDto.validateTaskPriority();
        TaskPriority taskPriority = TaskPriority.valueOf(taskDto.getTaskPriority());

        Tasks tasksToUpdate = repository.findById(taskId)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("Task not found with ID: " + taskId);
                });

        tasksToUpdate.setTaskTitle(taskDto.getTaskTitle());
        tasksToUpdate.setTaskDetails(taskDto.getTaskDetails());
        tasksToUpdate.setTaskPriority(taskPriority);
        tasksToUpdate.setTaskCompleted(taskDto.getTaskCompleted());

        return updateTask(taskId, tasksToUpdate);
    }

    @Override
    public List<Tasks> deleteTaskById(Long taskId) {
        if (repository.findById(taskId).isEmpty()) {
            throw new ResourceNotFoundException("Task not found with ID: " + taskId);
        } else {
            repository.deleteById(taskId);
        }

        return repository.findAll();
    }

    @Override
    public void deleteAllTasks() {
        repository.deleteAll();
    }
}
