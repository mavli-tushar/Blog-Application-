package com.example.blogApp.services.imp;

import com.example.blogApp.exception.ResourceNotFoundException;
import com.example.blogApp.model.Comment;
import com.example.blogApp.model.Post;
import com.example.blogApp.payload.CommentDto;
import com.example.blogApp.reposetory.CommentRepo;
import com.example.blogApp.reposetory.PostRepo;
import com.example.blogApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));
        Comment comment= this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savesComment= this.commentRepo.save(comment);

        return this.modelMapper.map(savesComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment id",commentId));
        this.commentRepo.delete(comment);
    }
}
