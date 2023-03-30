package com.epde.rt.service.assignments;

import com.epde.rt.exception.ResourceNotFoundException;
import com.epde.rt.model.assignments.Assignment;
import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.users.AppUsers;
import com.epde.rt.repository.AppUsersRepository;
import com.epde.rt.repository.AssignmentRepository;
import com.epde.rt.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final TasksRepository tasksRepository;

    private final AppUsersRepository usersRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, TasksRepository tasksRepository, AppUsersRepository usersRepository) {
        this.assignmentRepository = assignmentRepository;
        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Assignment assignTask(Long taskId, Long userId) {
        Optional<Tasks> optionalTask = tasksRepository.findById(taskId);
        Optional<AppUsers> optionalUser = usersRepository.findById(userId);

        if (optionalTask.isPresent() && optionalUser.isPresent()) {
            Tasks task = optionalTask.get();
            AppUsers user = optionalUser.get();

            Assignment assignment = new Assignment(task, user);

            return assignmentRepository.save(assignment);
        } else {
            throw new ResourceNotFoundException("Task with ID: " + taskId + "or User with ID " + userId + "NOT FOUND!");
        }
    }
}