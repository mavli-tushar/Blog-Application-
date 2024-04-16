package com.example.blogApp;

import com.example.blogApp.config.AppConstants;
import com.example.blogApp.model.Role;
import com.example.blogApp.reposetory.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApplication {

	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;


	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public void run(String... args) throws Exception{
		try {
			Role role1=new Role(AppConstants.ADMIN_USER,"ROLE_ADMIN");
			roleRepo.save(role1);
			Role role2=new Role(AppConstants.NORMAL_USER,"ROLE_NORMAL");
			roleRepo.save(role2);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
