package com.example.blogApp.controller;

import com.example.blogApp.model.Post;
import com.example.blogApp.payload.PostDto;
import com.example.blogApp.services.PostService;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
@Autowired
   private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto , @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createdPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
       List<PostDto> postDtos= this.postService.getPostByUser(userId);
       return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId){
        List<PostDto> postDtos= this.postService.getPostByCategory(catId);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }
}