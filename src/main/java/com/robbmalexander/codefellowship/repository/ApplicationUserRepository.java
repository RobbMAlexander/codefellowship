package com.robbmalexander.codefellowship.repository;

import com.robbmalexander.codefellowship.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUserName(String username);
}
