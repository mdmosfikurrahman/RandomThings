package com.epde.rt.service.users;

import com.epde.rt.dto.AppUserDto;
import com.epde.rt.model.users.AppUsers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUsersService {
    List<AppUsers> getAllUsers();

    AppUsers getUserById(Long userId);

    AppUsers getUserByUsername(String username);

    AppUsers createUser(AppUsers AppUsers);

    AppUsers updateUser(Long userId, AppUsers AppUsers);

    List<AppUsers> deleteUserById(Long userId);

    void deleteAllAppUsers();

    AppUsers addOrUpdate(AppUserDto appUserDto);
}
