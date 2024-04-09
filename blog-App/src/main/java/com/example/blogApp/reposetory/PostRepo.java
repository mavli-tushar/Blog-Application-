package com.example.blogApp.reposetory;

import com.example.blogApp.model.Category;
import com.example.blogApp.model.Post;
import com.example.blogApp.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category cat);
    
    List<Post> findByTitleContaining(String title);
}
