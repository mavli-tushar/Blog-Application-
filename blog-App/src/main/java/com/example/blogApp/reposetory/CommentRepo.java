package com.example.blogApp.reposetory;

import com.example.blogApp.model.Comment;
import com.example.blogApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {


    List<Comment> findByPost(Post post);
}
