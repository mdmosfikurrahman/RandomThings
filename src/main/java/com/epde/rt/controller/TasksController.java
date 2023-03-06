package com.epde.rt.controller;

import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.service.TasksService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TasksController {
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("/tasks")
    public String viewHomePage(Model model) {
        return findPaginated(1, "taskTitle", "asc", model);
    }

    // create model attribute to bind form data
    @GetMapping("/showNewTasksForm")
    public String showNewTasksForm(Model model) {
        Tasks tasks = new Tasks();
        model.addAttribute("tasks", tasks);
        return "tasks/new_task";
    }

    // save task to database
    @PostMapping("/saveTasks")
    public String saveTasks(@ModelAttribute("tasks") Tasks tasks) {
        tasksService.saveTasks(tasks);
        return "redirect:/tasks";
    }

    @GetMapping("/showTaskFormForUpdate/{taskId}")
    public String showFormForUpdate(@PathVariable( value = "taskId") long taskId, Model model) {

        // get task from the service
        Tasks tasks = tasksService.getTasksById(taskId);

        // set task as a model attribute to pre-populate the form
        model.addAttribute("tasks", tasks);
        return "tasks/update_task";
    }

    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable (value = "taskId") long taskId) {

        // call delete task method
        this.tasksService.deleteTaskById(taskId);
        return "redirect:/tasks";
    }

    @GetMapping("/taskPage/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page < Tasks > tasksPage = tasksService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Tasks > listTasks = tasksPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", tasksPage.getTotalPages());
        model.addAttribute("totalItems", tasksPage.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTasks", listTasks);
        return "tasks/task_index";
    }
}
