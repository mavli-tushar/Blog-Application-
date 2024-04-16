package com.example.blogApp.reposetory;

import com.example.blogApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User>findByEmail(String email);

}
