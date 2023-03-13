package com.epde.rt.repository;

import com.epde.rt.model.tasks.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
    Optional<Tasks> findByTaskTitle(String title);
}
