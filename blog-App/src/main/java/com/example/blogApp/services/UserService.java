package com.example.blogApp.services;

import com.example.blogApp.payload.UserDto;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateuser(UserDto user , Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);

}
