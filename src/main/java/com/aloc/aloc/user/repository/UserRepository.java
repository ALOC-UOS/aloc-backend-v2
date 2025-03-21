package com.aloc.aloc.user.repository;

import com.aloc.aloc.user.entity.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String githubId);
}
