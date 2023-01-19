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
@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {

    Tasks findByTaskId(Integer taskId);

    List<Tasks> findAllByOrderByTaskIdDesc();

    @Query("SELECT t FROM Tasks t WHERE t.taskId = :taskId")
    Tasks getTasksById(Integer taskId);

    Tasks findFirstByOrderByTaskIdDesc();

    @Query("SELECT t FROM Tasks t WHERE t.taskId = :taskId")
    Tasks getTasksByTaskId(Integer taskId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Tasks ORDER BY ID limit ? offset ?")
    List<Tasks> getAllTasks(Integer limit, Integer offset);

    @Transactional
    public void deleteByTaskId(Integer taskId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Tasks WHERE " +
                    "title LIKE %:search% " +
                    "or details LIKE %:search% " +
                    "or priority LIKE %:search% " +
                    "or status LIKE %:search% " +
                    "or ORDER by id desc limit :limit offset :offset")
    List<Tasks> getAllTasksByAll(String search, Integer limit, Integer offset);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM Tasks WHERE " +
            "title LIKE %:search% " +
            "or details LIKE %:search% " +
            "or priority LIKE %:search% " +
            "or status LIKE %:search% ")
    Integer getTasksListCount(String search);


}
