package com.epde.rt.controller;

import com.epde.rt.model.users.AppUsers;
import com.epde.rt.service.AppUsersService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppUsersController {
    private final AppUsersService usersService;

    public AppUsersController(AppUsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public String viewHomePage(Model model) {
        return findPaginated(
                1,
                "userFirstName",
                "asc",
                model);
    }

    // create model attribute to bind from data
    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        AppUsers users = new AppUsers();
        model.addAttribute("users", users);
        return "users/new_user";
    }

    // save users to database
    @PostMapping("/saveUsers")
    public String saveUsers(@ModelAttribute("users") AppUsers users) {
        usersService.saveUsers(users);
        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate/{userId}")
    public String showFormForUpdate(@PathVariable(value = "userId") long userId, Model model) {
        // get user from the service
        AppUsers users = usersService.getUserById(userId);

        // set user as a model attribute to pre-populate the form
        model.addAttribute("users", users);
        return "users/update_user";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable(value = "userId") long userId) {
        // call delete user method
        this.usersService.deleteUserById(userId);
        return "redirect:/users";
    }

    @GetMapping("/userPage/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<AppUsers> usersPage = usersService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List< AppUsers > appUsersList = usersPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("totalItems", usersPage.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("appUsersList", appUsersList);
        return "users/user_index";
    }
}
