package com.mydays.backend.repository;

import com.mydays.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
