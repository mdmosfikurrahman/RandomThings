package com.epde.rt.repository;

import com.epde.rt.model.users.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUsersRepository extends JpaRepository<AppUsers, Long> {
    Optional<AppUsers> findByUsername(String username);

}