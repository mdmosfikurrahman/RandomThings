package com.mosfik.random.service;

import com.mosfik.random.dto.TasksDto;
import com.mosfik.random.model.Tasks;
import com.mosfik.random.repository.TasksRepository;
import com.mosfik.random.utils.Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tasks Service.
 * @author Md. Mosfikur Rahman
 */

@Service
public class TasksService {
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy";
    private final TasksRepository tasksRepository;
    private final Utils utils;
    private static final Log log = LogFactory.getLog(TasksService.class);
    public TasksService(TasksRepository tasksRepository, Utils utils) {
        this.tasksRepository = tasksRepository;
        this.utils = utils;
    }

    /**
     * Init view for Tasks
     *
     * @param model Model
     */
    public void viewInit(Model model){
        List<TasksDto> tasksDtoList = new ArrayList<>();
        List<Tasks> tasksList = tasksRepository.findAllByOrderByTaskIdDesc();
        TasksDto tasksDto = null;
        if (tasksList != null && tasksList.size() > 0) {
            for (Tasks tasks : tasksList) {
                tasksDto = new TasksDto();
                tasksDto.setTaskId(tasks.getTaskId());
                tasksDto.setTaskTitle(tasks.getTaskTitle());
                tasksDto.setTaskDetails(tasks.getTaskDetails());
                tasksDto.setTaskPriority(tasks.getTaskPriority());
                tasksDto.setTaskStatus(tasks.getTaskStatus());
                tasksDto.setTaskIsActive(tasks.getTaskIsActive());

                tasksDtoList.add(tasksDto);
            }
        }
        model.addAttribute("tasksList", tasksDtoList);
    }

    /**
     * Get Tasks by taskId
     *
     * @param taskId
     * @return Tasks
     */

    public Tasks getTasksById(Integer taskId) {
        Tasks tasks = null;
        if (tasksRepository.findByTaskId(taskId) != null) {
            tasks = tasksRepository.findByTaskId(taskId);
        }
        return tasks;
    }

    /**
     * Service method of save and update
     *
     * @param model
     * @param tasksDto
     * @return Tasks
     */
    public void saveOrUpdate(Model model, TasksDto tasksDto) {
        try {
            Tasks tasks = new Tasks();

            if (tasksDto.getTaskDetails() == null) {
                tasks.setTaskCreatedAt(Utils.strToDt(Utils.getCurrentDateTime(), DATE_TIME_FORMAT));
            } else {
                Tasks task = tasksRepository.getTasksById(tasksDto.getTaskId());
                tasks.setTaskId(task.getTaskId());
                tasks.setTaskCreatedAt(task.getTaskCreatedAt());
                tasks.setTaskCompletedAt(task.getTaskCompletedAt());
                tasks.setTaskModifiedAt(Utils.strToDt(Utils.getCurrentDateTime(), DATE_TIME_FORMAT));
            }
            tasks.setTaskTitle(tasksDto.getTaskTitle());
            tasks.setTaskDetails(tasksDto.getTaskDetails());
            tasks.setTaskPriority(tasksDto.getTaskPriority());
            tasks.setTaskStatus(tasksDto.getTaskStatus());
            tasks.setTaskIsActive(tasksDto.getTaskIsActive());

            this.tasksRepository.save(tasks);

            model.addAttribute("tasks", tasks);
            model.addAttribute("formStatus", "success");
        } catch (Exception e) {
            model.addAttribute("formStatus", "fail");
            log.error(e, e);
        }
    }

    /**
     * Delete Tasks Service
     * @param taskId
     */
    public void deleteByTasksId(Integer taskId) {
        tasksRepository.deleteByTaskId(taskId);
    }

    /**
     * Get all Tasks for server side data table
     * @param search
     * @param limit
     * @param offset
     * @return Map
     */
    public Map<String, Object> getAllTasks(String search, Integer limit, Integer offset) {
        Long countTasks = tasksRepository.count();
        List<TasksDto> tasksDtoList = new ArrayList<>();
        List<Tasks> tasksList = null;
        Map<String, Object> map = new HashMap<>();

        if (!StringUtils.isEmpty(search)) {
            tasksList = tasksRepository.getAllTasksByAll(search, limit, offset);
            map.put("total", tasksRepository.getTasksListCount(search));
        } else {
            tasksList = tasksRepository.getAllTasks(limit, offset);
            map.put("total", countTasks);
        }

        TasksDto tasksDto = null;

        if (tasksList != null & tasksList.size() > 0) {
            for (Tasks tasks : tasksList) {
                tasksDto = new TasksDto();
                tasksDto.setTaskId(tasks.getTaskId());
                tasksDto.setTaskTitle(tasks.getTaskTitle());
                tasksDto.setTaskDetails(tasks.getTaskDetails());
                tasksDto.setTaskPriority(tasks.getTaskPriority());
                tasksDto.setTaskStatus(tasks.getTaskStatus());
                tasksDto.setTaskIsActive(tasks.getTaskIsActive());

                tasksDtoList.add(tasksDto);
            }
        }

        map.put("totalNotFiltered", countTasks);
        map.put("rows", tasksDtoList);
        return map;
    }
}
