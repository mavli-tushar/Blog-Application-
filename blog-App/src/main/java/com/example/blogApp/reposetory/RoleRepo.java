package com.example.blogApp.reposetory;

import com.example.blogApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {

}
