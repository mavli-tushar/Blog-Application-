package com.example.blogApp.payload;

import com.example.blogApp.model.Category;
import com.example.blogApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private String title;
    private String content;
    private String imgName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;


}
