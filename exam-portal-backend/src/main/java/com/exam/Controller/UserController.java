package com.exam.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.models.Roles;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/")
	public User createUser(@RequestBody User user) 
	{
		
		User u;

		System.out.println(user.toString());
		user.setProfile("default.png");

		user.setUserRoles(new HashSet<>());

		Set<UserRole> roles=new HashSet<>();
		Roles r=new Roles();
		r.setRoleId(45L);
		r.setRoleName("NORMAL");
         UserRole userRole=new UserRole();
         userRole.setRole(r);
         userRole.setUser(user);
         roles.add(userRole);
     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
        	return this.userServiceImpl.createuser(user, roles);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
        return null;
		
	}
	@GetMapping("/{userName}")
	public User get(@PathVariable("userName") String userName)
	{
		
		return this.userServiceImpl.getUserByname(userName);
	
		
	}
	@DeleteMapping("/{useId}")
	public void get(@PathVariable("useId") Long userId)
	{
		
	this.userServiceImpl.delete(userId);
	
		
	}
//	@ExceptionHandler(Exception e)
	
}
