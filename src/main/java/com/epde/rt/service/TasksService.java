package com.epde.rt.service;

import com.epde.rt.model.Tasks;
import com.epde.rt.repository.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    // to view all tasks in home page
    public List < Tasks > getAllTasks() {
        return tasksRepository.findAll();
    }


    public void saveTasks(Tasks tasks) {
        this.tasksRepository.save(tasks);
    }

    public Tasks getTasksById(long taskId) {
        Optional< Tasks > optional = tasksRepository.findById(taskId);
        Tasks tasks;
        if (optional.isPresent()) {
            tasks = optional.get();
        } else {
            throw new RuntimeException(" Tasks not found for ID :: " + taskId);
        }
        return tasks;
    }

    public void deleteTaskById(long taskId) {
        this.tasksRepository.deleteById(taskId);
    }

    public Page<Tasks> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.tasksRepository.findAll(pageable);
    }

}
