package com.example.blogApp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    private List<PostDto> content;
    private int pageSize;
    private int pageNo;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;


}
