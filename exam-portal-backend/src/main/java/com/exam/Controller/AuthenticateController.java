package com.exam.Controller;

import java.security.Principal;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Config.JwtUtils;
import com.exam.models.JwtRequest;
import com.exam.models.JwtResponse;
import com.exam.models.User;

@RestController
@CrossOrigin
public class AuthenticateController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtils jwtUtils;
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println(jwtRequest.toString());
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("user not found");
		}
		UserDetails userDetailsService=this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtils.generateToken(userDetailsService);
		JwtResponse jwtResponse=new JwtResponse(token);
		return ResponseEntity.ok(jwtResponse);
		 

		
	}
	
	private void authenticate(String username,String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("User Disabssled"+e.getMessage());
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Invalid credentials"+e.getMessage());
		}
		
	}
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
	{
		return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
	}
	
	
}
