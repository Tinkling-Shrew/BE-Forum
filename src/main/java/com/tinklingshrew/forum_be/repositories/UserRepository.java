package com.tinklingshrew.forum_be.repositories;

import com.tinklingshrew.forum_be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    void deleteUserById(Long userId);

    Optional<User> findByUsername(String username);
}
