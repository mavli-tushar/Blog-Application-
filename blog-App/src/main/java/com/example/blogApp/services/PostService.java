package com.example.blogApp.services;

import com.example.blogApp.model.Post;
import com.example.blogApp.payload.PostDto;
import com.example.blogApp.reposetory.PostRepo;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);

    Post updatePost(PostDto postDto ,Integer postId);

    void deletePost(Integer postId);
    List<Post> getAllPost();

    Post gePostById(Integer postId);

    List<PostDto> getPostByCategory(Integer catId);

    List<PostDto> getPostByUser(Integer userId);


    List<Post> searchPost(String keyword);
}
