package com.example.blogApp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "user must me minimum of 4 characters")
    private String name;
    @Email(message = "Email addres is not valid !!")
    private String email;
    @NotEmpty
    @Size(min = 3 ,max = 10,message = "Password must be minimum of 3 characters and maximum of 10 characters")
    private String password;
    @NotEmpty
    private String about;
}
