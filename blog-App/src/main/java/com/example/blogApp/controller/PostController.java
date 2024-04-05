package com.example.blogApp.controller;

import com.example.blogApp.model.Post;
import com.example.blogApp.payload.ApiResponse;
import com.example.blogApp.payload.PostDto;
import com.example.blogApp.services.PostService;
import org.apache.catalina.util.Introspection;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
//    @GetMapping("/posts")
//    public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value = "pageNo",defaultValue = "1",required = false)Integer pageNo,
//                                                    @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize){
//        List<PostDto>postDtos=this.postService.getAllPost(pageNo,pageSize);
//        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
//    }
        @GetMapping("/posts")
        public ResponseEntity<List<PostDto>> getAllPost(@RequestParam (defaultValue = "0") int page,
                                                        @RequestParam (defaultValue = "10")int size){
            Pageable pageable= PageRequest.of(page,size);
            List<PostDto>postDtos=this.postService.getAllPost(pageable);
            return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
        }


    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
       PostDto postDto= this.postService.getPostById(postId);
       return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post Is Successfully deleted !!",true);

    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost( @RequestBody PostDto postDto, @PathVariable Integer postId){
       PostDto updatedPost= this.postService.updatePost(postDto,postId);
       return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }
}
