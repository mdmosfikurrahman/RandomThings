package com.epde.rt.service.assignments;

import com.epde.rt.exception.ResourceAlreadyExistsException;
import com.epde.rt.exception.ResourceNotFoundException;
import com.epde.rt.model.assignments.Assignment;
import com.epde.rt.model.assignments.AssignmentResponse;
import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.users.AppUsers;
import com.epde.rt.repository.AppUsersRepository;
import com.epde.rt.repository.AssignmentRepository;
import com.epde.rt.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Assignment> getAllAssignments() {
        List<Assignment> assignmentList;
        if (assignmentRepository.count() != 0) {
            assignmentList = assignmentRepository.findAll();
        } else {
            throw new ResourceNotFoundException("No Assignments Found!");
        }

        return assignmentList;
    }

    @Override
    public AssignmentResponse getAssignmentById(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Assignment not found with ID: " + assignmentId);
        });

        return createAssignmentResponse(assignment);
    }

    @Override
    public AssignmentResponse assignTask(Long taskId, Long userId) {
        Optional<Tasks> optionalTask = tasksRepository.findById(taskId);
        Optional<AppUsers> optionalUser = usersRepository.findById(userId);

        if (optionalTask.isPresent() && optionalUser.isPresent()) {
            Tasks task = optionalTask.get();
            AppUsers user = optionalUser.get();

            // Check if assignment already exists
            Optional<Assignment> existingAssignment = assignmentRepository.findByTaskAndUser(task, user);
            if (existingAssignment.isPresent()) {
                throw new ResourceAlreadyExistsException("Task with ID: " + taskId + " is already assigned to User with ID: " + userId);
            }

            Assignment assignment = new Assignment(task, user);
            assignmentRepository.save(assignment);

            return createAssignmentResponse(assignment);
        } else {
            if (optionalTask.isPresent()) {
                throw new ResourceNotFoundException("User not found with ID: " + userId);
            } else if (optionalUser.isPresent()) {
                throw new ResourceNotFoundException("Task not found with ID: " + taskId);
            }

            throw new ResourceNotFoundException("Task with ID: " + taskId + " and User with ID " + userId + " NOT FOUND!");
        }
    }

    @Override
    public List<Assignment> deleteAssignmentById(Long assignmentId) {
        if (assignmentRepository.findById(assignmentId).isEmpty()) {
            throw new ResourceNotFoundException("Assignment not found with ID: " + assignmentId);
        } else {
            assignmentRepository.deleteById(assignmentId);
        }

        return assignmentRepository.findAll();
    }

    @Override
    public void deleteAllAssignments() {
        assignmentRepository.deleteAll();
    }

    private AssignmentResponse createAssignmentResponse(Assignment assignment) {
        return new AssignmentResponse(assignment.getAssignmentId(), assignment.getTask().getTaskTitle(), assignment.getTask().getTaskPriority(), assignment.getTask().getTaskCompleted(), assignment.getUser().getUserFirstName());
    }
}