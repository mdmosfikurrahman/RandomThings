package com.mosfik.random.service;

import com.mosfik.random.dto.TasksDto;
import com.mosfik.random.model.Tasks;
import com.mosfik.random.repository.TasksRepository;
import com.mosfik.random.utils.Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    private static final Log log = LogFactory.getLog(TasksService.class);

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    /**
     * Get Tasks list
     *
     * @return String
     */

    public String getTaskList() {
        List<Tasks> tasksList = tasksRepository.findAll();

        StringBuilder stringBuilder = new StringBuilder("<option value=\"\">---</option>");
        if(tasksList != null && tasksList.size() > 0) {
            for (Tasks tasks : tasksList) {
                String option = "<option value=\"" + tasks.getTaskId() + "\">" + tasks.getTaskTitle() + "</option>";
                stringBuilder.append(option);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Init view for Tasks
     *
     * @param model Model
     */

    public void viewInit(Model model) {
        List<TasksDto> tasksDtoList = new ArrayList<>();
        List<Tasks> tasksList = tasksRepository.findAllByOrderByTaskIdDesc();
        TasksDto tasksDto = null;

        for (Tasks tasks : tasksList) {
            tasksDto = new TasksDto();
            tasksDto.setTaskId(tasks.getTaskId());
            tasksDto.setTaskTitle(tasks.getTaskTitle());
            tasksDto.setTaskDetails(tasks.getTaskDetails());
            tasksDto.setPriority(tasks.getPriority());
            tasksDto.setIsActive(tasks.getIsActive());

            tasksDtoList.add(tasksDto);
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
                tasks.setCreatedAt(Utils.strToDt(Utils.getCurrentDateTime(), DATE_TIME_FORMAT));
            } else {
                Tasks task = tasksRepository.getTasksById(tasksDto.getTaskId());
                tasks.setTaskId(task.getTaskId());
                tasks.setCreatedAt(task.getCreatedAt());
                tasks.setCompletedAt(task.getCompletedAt());
                tasks.setModifiedAt(Utils.strToDt(Utils.getCurrentDateTime(), DATE_TIME_FORMAT));
            }
            tasks.setTaskTitle(tasksDto.getTaskTitle());
            tasks.setTaskDetails(tasksDto.getTaskDetails());
            tasks.setPriority(tasksDto.getPriority());
            tasks.setIsActive(tasksDto.getIsActive());

            this.tasksRepository.save(tasks);

            model.addAttribute("tasks", tasks);
            model.addAttribute("formStatus", "success");
        } catch (Exception e) {
            model.addAttribute("formStatus", "fail");
            log.error(e, e);
        }
    }

    /**
     * Get Tasks by taskId
     * @param taskId
     * @return Map
     */

    public Map<String, Object> getTasksByTaskId(Integer taskId) {
        Map<String, Object> taskMap = new HashMap<>();

        Tasks tasks = tasksRepository.findByTaskId(taskId);
        if (tasks != null) {
            taskMap.put("taskTitle", tasks.getTaskTitle());
            taskMap.put("taskDetails", tasks.getTaskDetails());
            taskMap.put("priority", tasks.getPriority());
            taskMap.put("isActive", tasks.getIsActive());
        }

        return taskMap;
    }

    /**
     * Delete Tasks Service
     * @param taskId
     */
    public void deleteByTasksId(Integer taskId) {
        tasksRepository.deleteByTaskId(taskId);
    }

}
