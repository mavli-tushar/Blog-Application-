package com.example.blogApp.controller;

import com.example.blogApp.Security.JwtTokenHelper;
import com.example.blogApp.exception.ApiException;
import com.example.blogApp.payload.JwtAuthRequest;
import com.example.blogApp.payload.JwtAuthResponse;
import com.example.blogApp.payload.UserDto;
import com.example.blogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
        this.authenticat(request.getUserName(),request.getPassword());

        UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUserName());
        String token= this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response= new JwtAuthResponse(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticat(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
            try {
                this.authenticationManager.authenticate(authenticationToken);
            }catch (BadCredentialsException e){
                System.out.println("Invalid Details");
                throw new ApiException("Invalid User Name Or Password");
            }
    }

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    private ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registerUser = this.userService.registerNewUser(userDto);
        return  new ResponseEntity<>(registerUser,HttpStatus.CREATED);
    }
}
