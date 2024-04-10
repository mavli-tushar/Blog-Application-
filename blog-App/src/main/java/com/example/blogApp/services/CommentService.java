package com.example.blogApp.services;

import com.example.blogApp.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto ,Integer postId);
    void  deleteComment(Integer commentId);

}
