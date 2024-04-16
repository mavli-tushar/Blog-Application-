package com.example.blogApp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JwtAuthRequest {
    private String userName;
    private String password;
}
