package com.example.blogApp.services.imp;

import com.example.blogApp.exception.ResourceNotFoundException;
import com.example.blogApp.model.Category;
import com.example.blogApp.model.Post;
import com.example.blogApp.model.User;
import com.example.blogApp.payload.PostDto;
import com.example.blogApp.reposetory.CategoryRepo;
import com.example.blogApp.reposetory.PostRepo;
import com.example.blogApp.reposetory.UserRepo;
import com.example.blogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId )
    {
        User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));

        Post post= this.modelMapper.map(postDto,Post.class);
        post.setImgName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post createdPost= this.postRepo.save(post);


        return this.modelMapper.map(createdPost,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post gePostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer catId) {
        Category cat=this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id ",catId));
        List<Post> posts= this.postRepo.findByCategory(cat);

        List<PostDto> postDtos= posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));

        List<Post>posts=this.postRepo.findByUser(user);

        List<PostDto> postDtos= posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
