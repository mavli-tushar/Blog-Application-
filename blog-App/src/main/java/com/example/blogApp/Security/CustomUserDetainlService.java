package com.example.blogApp.Security;

import com.example.blogApp.exception.ResourceNotFoundException;
import com.example.blogApp.model.User;
import com.example.blogApp.reposetory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetainlService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email:"+username,0));
        return user;
    }
}
