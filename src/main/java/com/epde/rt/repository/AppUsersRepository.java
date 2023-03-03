package com.epde.rt.repository;

import com.epde.rt.model.users.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUsersRepository extends JpaRepository<AppUsers, Long> {

}