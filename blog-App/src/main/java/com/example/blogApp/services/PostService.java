package com.example.blogApp.services;

import com.example.blogApp.model.Post;
import com.example.blogApp.payload.PostDto;
import com.example.blogApp.payload.PostResponse;
import com.example.blogApp.reposetory.PostRepo;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto ,Integer postId);

    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sorDir);
//    List<PostDto> getAllPost(Pageable pageable);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer catId);

    List<PostDto> getPostByUser(Integer userId);


    List<PostDto> searchPost(String keyword);
}
