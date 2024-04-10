package com.example.blogApp.controller;

import com.example.blogApp.model.Comment;
import com.example.blogApp.payload.ApiResponse;
import com.example.blogApp.payload.CommentDto;
import com.example.blogApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;
@PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
       CommentDto createdComment= this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    @DeleteMapping("/comments/{comId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer comId){
        this.commentService.deleteComment(comId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfuly",true), HttpStatus.OK);
    }
}
