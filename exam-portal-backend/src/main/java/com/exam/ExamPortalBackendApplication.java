package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.models.Roles;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.impl.UserServiceImpl;

@SpringBootApplication
public class ExamPortalBackendApplication {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args){
		SpringApplication.run(ExamPortalBackendApplication.class, args);
		
		System.out.println("jfjfjsddsdjxxxsxxxxdddscddsddddddccdddcaaasdddx");
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		User user=new User();
//		user.setFirstName("tejeswara Reddy");
//		user.setPassword(this.passwordEncoder.encode("123"));
//		user.setEmail("gvsrgaja1966@gmail.com");
//		user.setProfile("default.png");
//		user.setUsername("teja");
//		user.setUserRoles(new HashSet<>());
//		Roles role=new Roles();
//		role.setRoleName("ADMIN");
//		role.setRoleId(44L);
//		Set<UserRole> userRoles=new HashSet<>();
//		UserRole userRole=new UserRole();
//		userRole.setUser(user);
//		userRole.setRole(role);
//userRoles.add(userRole);
//		this.userServiceImpl.createuser(user, userRoles);
//		
//		
//		
//	}

}
