package com.mosfik.random.repository;

import com.mosfik.random.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Tasks Repository.
 * @author Md. Mosfikur Rahman
 */

public interface TasksRepository extends JpaRepository<Tasks, Integer> {

    Tasks findByTaskId(Integer taskId);

    List<Tasks> findAllByOrderByTaskIdDesc();

    Tasks findFirstByOrderByTaskIdDesc();

    @Transactional
    public void deleteByTaskId(Integer taskId);

    @Query(nativeQuery = true,
            value = "SELECT t FROM Tasks t WHERE c.taskId = :taskId")
    Tasks getTasksById(Integer taskId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Tasks order by ID limit ? offset ?")
    List<Tasks> getAllTasks(Integer limit, Integer offset);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Tasks WHERE " +
            "TITLE LIKE %:search% " +
            "or DETAILS LIKE %:search% " +
            "or ORDER by ID desc limit :limit offset :offset")
    List<Tasks> getAllTasksByAll(String search, Integer limit, Integer offset);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM Tasks WHERE " +
            "TITLE LIKE %:search% " +
            "or DETAILS LIKE %:search% ")
    Integer getTasksListCount(String search);


}
