package com.example.blogApp.reposetory;

import com.example.blogApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category ,Integer> {
}
