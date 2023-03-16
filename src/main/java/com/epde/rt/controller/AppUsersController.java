package com.epde.rt.controller;

import com.epde.rt.dto.AppUserDto;
import com.epde.rt.model.users.AppUsers;
import com.epde.rt.service.users.AppUsersServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUsersController {
    private final AppUsersServiceImpl appUsersService;

    public AppUsersController(AppUsersServiceImpl appUsersService) {
        this.appUsersService = appUsersService;
    }

    @GetMapping
    public List<AppUsers> getAllUsers() {
        return appUsersService.getAllUsers();
    }

    @GetMapping("/id-{userId}")
    public AppUsers getUserById(@PathVariable Long userId) {
        return appUsersService.getUserById(userId);
    }

    @GetMapping("/{username}")
    public AppUsers getUserByUsername(@PathVariable String username) {
        return appUsersService.getUserByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUsers createUser(@Valid @RequestBody AppUserDto appUserDto) {
        AppUsers appUsers = appUsersService.addOrUpdate(appUserDto);
        return appUsersService.createUser(appUsers);
    }

    @PutMapping("/id-{userId}")
    public AppUsers updateUser(@PathVariable Long userId, @Valid @RequestBody AppUserDto appUserDto) {
        AppUsers users = appUsersService.addOrUpdate(appUserDto);
        return appUsersService.updateUser(userId, users);
    }

    @DeleteMapping("/id-{userId}")
    public List<AppUsers> deleteUserById(@PathVariable Long userId) {
        return appUsersService.deleteUserById(userId);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        appUsersService.deleteAllAppUsers();
    }
}
