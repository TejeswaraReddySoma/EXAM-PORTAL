package com.exam.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.exam.models.Roles;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository rolerepository;

	@Override
	public User createuser(User user, Set<UserRole> userRoles) throws Exception
	{
		System.out.println("in Repositoryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
User u=userRepository.findByUserName(user.getUsername());
		if(u!=null)
		{
			System.out.println("user is also there");
			throw new Exception("user alreday there");
		}
		else
		{
			System.out.println("in Repositoryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
			
			
			for(UserRole ur:userRoles)
			{
				Roles r=((CrudRepository<Roles, Long>) this.rolerepository).save(ur.getRole());
			}
		
			Set<UserRole> set=user.getUserRoles();
					set.addAll(userRoles);
					System.out.println(user.toString());
			 u=this.userRepository.save(user);
		}
		return u;
	
	}

	@Override
	public User getUserByname(String name) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUserName(name);
		
	}

	@Override
	public void delete(Long id) {
		this.userRepository.deleteById(id);
		
	}
	

}
