package com.epde.rt.controller;

import com.epde.rt.dto.AppUserDto;
import com.epde.rt.model.users.AppUsers;
import com.epde.rt.service.users.AppUsersServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class AppUsersController {
    private final AppUsersServiceImpl service;

    public AppUsersController(AppUsersServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<AppUsers> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/id-{userId}")
    public AppUsers getUserById(@PathVariable Long userId) {
        return service.getUserById(userId);
    }

    @GetMapping("/{username}")
    public AppUsers getUserByUsername(@PathVariable String username) {
        return service.getUserByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUsers createUser(@RequestBody Map<String, Object> userData) {
        ObjectMapper objectMapper = new ObjectMapper();
        AppUserDto appUserDto = objectMapper.convertValue(userData, AppUserDto.class);
        AppUsers appUsers = service.addMethod(appUserDto);
        return service.createUser(appUsers);
    }

    @PutMapping("/id-{userId}")
    public AppUsers updateUser(@PathVariable Long userId, @Valid @RequestBody Map<String, Object> userData) {
        ObjectMapper objectMapper = new ObjectMapper();
        AppUserDto appUserDto = objectMapper.convertValue(userData, AppUserDto.class);
        AppUsers appUsers = service.updateMethod(userId, appUserDto);
        return service.updateUser(userId, appUsers);
    }

    @DeleteMapping("/id-{userId}")
    public List<AppUsers> deleteUserById(@PathVariable Long userId) {
        return service.deleteUserById(userId);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        service.deleteAllAppUsers();
    }
}
