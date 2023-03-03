package com.epde.rt.service;

import com.epde.rt.model.tasks.Tasks;
import com.epde.rt.model.users.AppUsers;
import com.epde.rt.repository.AppUsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUsersService {
    private final AppUsersRepository usersRepository;

    public AppUsersService(AppUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // to view all users
    public List<AppUsers> getAllUsers() {
        return usersRepository.findAll();
    }

    public void saveUsers(AppUsers users) {
        this.usersRepository.save(users);
    }

    public AppUsers getUserById(long userId) {
        Optional<AppUsers> optionalAppUsers = usersRepository.findById(userId);
        AppUsers appUsers;
        if (optionalAppUsers.isPresent()) {
            appUsers = optionalAppUsers.get();
        } else {
            throw new RuntimeException("User not found for ID :: " + userId);
        }
        return appUsers;
    }

    public void deleteUserById(long userId) {
        this.usersRepository.deleteById(userId);
    }

    public Page<AppUsers> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.usersRepository.findAll(pageable);
    }
}
