package com.mosfik.random.controller;

import com.mosfik.random.dto.TasksDto;
import com.mosfik.random.model.Tasks;
import com.mosfik.random.service.TasksService;
import com.mosfik.random.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.Min;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Tasks Controller.
 * @author Md. Mosfikur Rahman
 */

@Controller
public class TasksController {
    private final TasksService tasksService;
    private final Utils utils;

    /**
     * Dependency injection via autowire.
     * @param tasksService
     * @param utils
     */
    public TasksController(TasksService tasksService, Utils utils) {
        this.tasksService = tasksService;
        this.utils = utils;
    }

    /**
     * Initialize View for Tasks
     * @return String
     */

    @GetMapping("task")
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
    @GetMapping("task/{tasksId}")
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
    @GetMapping("task/create")
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
    @GetMapping("task/create/{tasksId}")
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
     * @param redirectAttributes
     * @return String
     */

    @ResponseBody
    @PostMapping(value = "task/create", consumes = "application/json")
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
    @GetMapping("task/delete/{tasksId}")
    public String getDeleteTasks(Model model,
                          @PathVariable("tasksId") Integer tasksId) {
        tasksService.deleteByTasksId(tasksId);
        return "redirect:/tasks";
    }

    @GetMapping("tasks")
    public ResponseEntity<Map<String, Object>> getTasks(@RequestParam(value = "search") String search,
                                                        @RequestParam(value = "limit", required = false) Integer limit,
                                                        @RequestParam(value = "offset", required = false) @Min(0) Integer offset) {
        return new ResponseEntity<>(tasksService.getAllTasks(search, limit, offset), HttpStatus.OK);
    }

}
