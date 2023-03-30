package com.epde.rt.repository;

import com.epde.rt.model.assignments.Assignment;
import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.users.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByTaskAndUser(Tasks task, AppUsers user);
}