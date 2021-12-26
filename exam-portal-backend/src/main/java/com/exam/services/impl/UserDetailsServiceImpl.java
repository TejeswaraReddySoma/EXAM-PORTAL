package com.exam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.exam.models.User;
import com.exam.repo.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	User user=this.userRepository.findByUserName(username);
		if(user==null)
		{
			System.out.println("user name not found");
			throw new UsernameNotFoundException("No user found");
			
		}
		return user;
		
	}

	
}
