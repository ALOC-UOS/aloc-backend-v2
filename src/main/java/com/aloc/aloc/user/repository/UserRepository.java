package com.aloc.aloc.user.repository;

import com.aloc.aloc.user.entity.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String githubId);
    Optional<User> findByGithubId(String githubId);

}
