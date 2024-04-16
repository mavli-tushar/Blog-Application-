package com.example.blogApp.services.imp;

import com.example.blogApp.config.AppConstants;
import com.example.blogApp.exception.ResourceNotFoundException;
import com.example.blogApp.model.Role;
import com.example.blogApp.model.User;
import com.example.blogApp.payload.UserDto;
import com.example.blogApp.reposetory.RoleRepo;
import com.example.blogApp.reposetory.UserRepo;
import com.example.blogApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user= this.modelMapper.map(userDto,User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
       User updatedUser= this.userRepo.save(user);
       return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateuser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser=this.userRepo.save(user);
        UserDto userDto1= this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setAbout((userDto.getAbout()));
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        return  user;
    }
    private UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setAbout((user.getAbout()));
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
        return  userDto;
    }
}
