package com.epde.rt.service.users;

import com.epde.rt.dto.AppUserDto;
import com.epde.rt.exception.ResourceAlreadyExistsException;
import com.epde.rt.exception.ResourceNotFoundException;
import com.epde.rt.model.users.AppUsers;
import com.epde.rt.model.users.enums.AppUserGender;
import com.epde.rt.model.users.enums.AppUserRole;
import com.epde.rt.repository.AppUsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUsersServiceImpl implements AppUsersService {
    private final AppUsersRepository repository;

    public AppUsersServiceImpl(AppUsersRepository repository) {
        this.repository = repository;
    }

    /**
     * @return usersList - List of Users from Repository
     * @throws ResourceNotFoundException if no Users Found in the Database
     */
    @Override
    public List<AppUsers> getAllUsers() {
        List<AppUsers> usersList;
        if (repository.count() != 0) {
            usersList = repository.findAll();
        } else {
            throw new ResourceNotFoundException("No Users Found!");
        }
        return usersList;
    }

    /**
     * @param userId User ID
     * @return userById - Required User Information with User ID
     * @throws ResourceNotFoundException If no user found with the provided ID
     */
    @Override
    public AppUsers getUserById(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    /**
     * @param username User's username
     * @return userByUsername - Required User Information with username
     * @throws ResourceNotFoundException If no user found with the provided ID
     */
    @Override
    public AppUsers getUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    /**
     * @param appUsers It will Pass the values to create object for AppUsers
     * @return appUsers
     */
    @Override
    public AppUsers createUser(AppUsers appUsers) {
        Optional<AppUsers> username = repository.findByUsername(appUsers.getUsername());
        if (username.isPresent()) {
            throw new ResourceAlreadyExistsException("User already exists with username: " + appUsers.getUsername());
        }
        repository.save(appUsers);
        return appUsers;
    }

    /**
     * @param appUserDto DTO (Data Transfer Object) will be passed due to Validate and Helping Method for Create
     * @return AppUsers - Transferred and verified User Object
     */
    @Override
    public AppUsers addMethod(AppUserDto appUserDto) {
        appUserDto.validateUserGender();
        AppUserGender appUserGender = AppUserGender.valueOf(appUserDto.getUserGender());

        appUserDto.validateUserRole();
        AppUserRole appUserRole = AppUserRole.valueOf(appUserDto.getUserRole());

        return new AppUsers(appUserDto.getUserFirstName(), appUserDto.getUserLastName(), appUserDto.getUserEmail(), appUserGender, appUserDto.getUserAddress(), appUserDto.getUserContactNumber(), appUserDto.getUsername(), appUserDto.getPassword(), appUserRole, appUserDto.getUserDateOfBirth());
    }

    /**
     * @param userId   User ID
     * @param appUsers It will Pass the values to update object for AppUsers
     * @return appUsers
     */
    @Override
    public AppUsers updateUser(Long userId, AppUsers appUsers) {
        Optional<AppUsers> optionalAppUser = repository.findById(userId);
        if (optionalAppUser.isPresent()) {
            appUsers.setUserId(userId);
            Optional<AppUsers> username = repository.findByUsername(appUsers.getUsername());
            if (username.isPresent()) {
                throw new ResourceAlreadyExistsException("User already exists with username: " + appUsers.getUsername());
            } else {
                repository.save(appUsers);
            }
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }

        return appUsers;
    }

    /**
     * @param userId     User ID
     * @param appUserDto DTO (Data Transfer Object) will be passed due to Validate and Helping Method for Create
     * @return AppUsers - Transferred and verified User Object
     */
    @Override
    public AppUsers updateMethod(Long userId, AppUserDto appUserDto) {
        appUserDto.validateUserGender();
        AppUserGender appUserGender = AppUserGender.valueOf(appUserDto.getUserGender());

        appUserDto.validateUserRole();
        AppUserRole appUserRole = AppUserRole.valueOf(appUserDto.getUserRole());

        AppUsers userToUpdate = repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        userToUpdate.setUserFirstName(appUserDto.getUserFirstName());
        userToUpdate.setUserLastName(appUserDto.getUserLastName());
        userToUpdate.setUserEmail(appUserDto.getUserEmail());
        userToUpdate.setUserGender(appUserGender);
        userToUpdate.setUserAddress(appUserDto.getUserAddress());
        userToUpdate.setUserContactNumber(appUserDto.getUserContactNumber());
        userToUpdate.setUsername(appUserDto.getUsername());
        userToUpdate.setPassword(appUserDto.getPassword());
        userToUpdate.setUserRole(appUserRole);
        userToUpdate.setUserDateOfBirth(appUserDto.getUserDateOfBirth());

        return updateUser(userId, userToUpdate);
    }

    /**
     * @param userId User ID
     * @return appUsers
     */
    @Override
    public List<AppUsers> deleteUserById(Long userId) {
        if (repository.findById(userId).isEmpty()) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        } else {
            repository.deleteById(userId);
        }
        return repository.findAll();
    }

    /**
     * Delete All Users
     */
    @Override
    public void deleteAllAppUsers() {
        if (repository.count() != 0) {
            repository.deleteAll();
        } else {
            throw new ResourceNotFoundException("No Users Found!");
        }
    }
}
