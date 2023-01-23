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

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "taskTitle", "asc", model);
    }

    @GetMapping("/error")
    public String errorPage(){
        return "404";
    }

    // create model attribute to bind form data
    @GetMapping("/showNewTasksForm")
    public String showNewTasksForm(Model model) {
        Tasks tasks = new Tasks();
        model.addAttribute("tasks", tasks);
        return "tasks/new_tasks";
    }

    // save task to database
    @PostMapping("/saveTasks")
    public String saveTasks(@ModelAttribute("tasks") Tasks tasks) {
        tasksService.saveTasks(tasks);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{taskId}")
    public String showFormForUpdate(@PathVariable( value = "taskId") long taskId, Model model) {

        // get task from the service
        Tasks tasks = tasksService.getTasksById(taskId);

        // set task as a model attribute to pre-populate the form
        model.addAttribute("tasks", tasks);
        return "tasks/update_tasks";
    }

    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable (value = "taskId") long taskId) {

        // call delete task method
        this.tasksService.deleteTaskById(taskId);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page < Tasks > page = tasksService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Tasks > listTasks = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listTasks", listTasks);
        return "tasks/task_index";
    }
}
