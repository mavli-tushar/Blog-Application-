package com.example.blogApp.controller;

import com.example.blogApp.config.AppConstants;
import com.example.blogApp.model.Post;
import com.example.blogApp.payload.ApiResponse;
import com.example.blogApp.payload.PostDto;
import com.example.blogApp.payload.PostResponse;
import com.example.blogApp.services.FileServices;
import com.example.blogApp.services.PostService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.util.Introspection;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import java.awt.event.InputEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
@Autowired
   private PostService postService;
    @Autowired
    private FileServices fileServices;
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
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNo",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNo,
                                                   @RequestParam(value = "pageSize",required = false,defaultValue = AppConstants.PAGE_SIZE)Integer pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        PostResponse postResponse= this.postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
//        @GetMapping("/posts")
//        public ResponseEntity<List<PostDto>> getAllPost(@RequestParam (defaultValue = "0") int page,
//                                                        @RequestParam (defaultValue = "10")int size){
//            Pageable pageable= PageRequest.of(page,size);
//            List<PostDto>postDtos=this.postService.getAllPost(pageable);
//            return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
//        }
//

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

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
        List<PostDto> result=this.postService.searchPost(keyword);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }
    @Value("${project.image}")
    private String path;
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImg(
            @RequestParam("image") MultipartFile image,
            @PathVariable Integer  postId
            ) throws IOException {
        PostDto postDto= this.postService.getPostById(postId);
        String fileName =this.fileServices.uploadImg(path,image);
        postDto.setImgName(fileName);
        PostDto postDto1= this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);

    }
    @GetMapping(value ="/post/image/{imgName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imgName, HttpServletResponse response) throws IOException{
        InputStream resource=this.fileServices.getResource(path,imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}
