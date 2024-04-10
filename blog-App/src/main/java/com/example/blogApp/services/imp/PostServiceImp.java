package com.example.blogApp.services.imp;

import com.example.blogApp.exception.ResourceNotFoundException;
import com.example.blogApp.model.Category;
import com.example.blogApp.model.Post;
import com.example.blogApp.model.User;
import com.example.blogApp.payload.PostDto;
import com.example.blogApp.payload.PostResponse;
import com.example.blogApp.reposetory.CategoryRepo;
import com.example.blogApp.reposetory.CommentRepo;
import com.example.blogApp.reposetory.PostRepo;
import com.example.blogApp.reposetory.UserRepo;
import com.example.blogApp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImgName(postDto.getImgName());

        Post updatedPost= this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize , String sortBy, String sortDir) {
        Sort  sort= null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable p =PageRequest.of(pageNo,pageSize,sort);
       Page<Post> pagePost = this.postRepo.findAll(p);

       List<Post>posts= pagePost.getContent();
       List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

//    @Override
//    public List<PostDto> getAllPost(Pageable pageable) {
//        Page<Post> posts =this.postRepo.findAll(pageable);
//        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
//
//        return postDtos;
//    }


    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id",postId));
        return this.modelMapper.map(post,PostDto.class);
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
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts= this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos =posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }
}
