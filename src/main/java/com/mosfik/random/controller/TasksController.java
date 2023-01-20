package com.mosfik.random.controller;

import com.mosfik.random.dto.TasksDto;
import com.mosfik.random.model.Tasks;
import com.mosfik.random.service.TasksService;
import com.mosfik.random.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Tasks Repository.
 * @author Md. Mosfikur Rahman
 */
@Controller
public class TasksController {
    private final TasksService tasksService;
    private final Utils utils;

    /**
     * Dependency injection via autowire.
     *
     * @param tasksService
     * @param utils
     */
    public TasksController(TasksService tasksService, Utils utils) {
        this.tasksService = tasksService;
        this.utils = utils;
    }

    /**
     * Initialize View for Tasks..
     *
     * @return String
     */

    @GetMapping("tasks")
    public String initializeView() {
        return "tasks/tasksList";
    }

    /**
     * Get Tasks by Id
     * @param model
     * @param taskId
     * @return Map
     */
    @ResponseBody
    @GetMapping("tasks/{tasksId}")
    public Map<String, Object> getTasksById(Model model,
                                            @PathVariable("tasksId") Integer taskId) {
        Map<String, Object> map = new HashMap<>();
        Tasks tasks = tasksService.getTasksById(taskId);
        map.put("tasksTitle", tasks.getTaskTitle());
        map.put("tasksDetails", tasks.getTaskDetails());
        map.put("taskPriority", tasks.getTaskPriority());
        map.put("taskStatus", tasks.getTaskStatus());
        map.put("taskIsActive", tasks.getTaskIsActive());

        return map;
    }

    /**
     * Create View Init
     * @param model
     * @return String
     */
    @GetMapping("tasks/create")
    public String getCreateTasks(Model model) {
        model.addAttribute("tasksDto", new TasksDto());
        return "tasks/addTasks";
    }

    /**
     * Update view init
     * @poram model
     * @param tasksId
     * @return String
     */
    @GetMapping("tasks/create/{tasksId}")
    public String getUpdateTasks(Model model,
                                 @PathVariable("tasksId") Integer tasksId) {
        this.tasksService.viewInit(model);
        Tasks tasksDto = tasksService.getTasksById(tasksId);
        model.addAttribute("tasksDto", tasksDto);

        return "tasks/addTasks";
    }

    /**
     * Save and Update Method
     * @param model
     * @param tasksDto
     * @param bindingResult
     * @param redirectAttrs
     * @return String
     */

    @ResponseBody
    @PostMapping(value = "tasks/addTasks", consumes = "application/json")
    public String saveOrUpdateTasks(Model model,
                                    @RequestBody TasksDto tasksDto,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("status", "fail");
            return "tasks/addTasks";
        }

        tasksService.saveOrUpdate(model, tasksDto);

        return "success";
    }

    /**
     * Delete Tasks by Id
     *
     * @param model
     * @param tasksId
     * @return String
     */

}
