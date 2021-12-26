package com.exam.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.services.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		final String requestTokenHeader=request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null)
		{
			jwtToken=requestTokenHeader.substring(7);
		try {
			username=this.jwtUtils.extractUsername(jwtToken);
			
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			System.out.println("token expired");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("error");
		}
		}
		else
		{
		System.out.println("Invalid token");
		}
		if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
		{
			final UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(username);
			if(this.jwtUtils.validateToken(jwtToken, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		else
		{
			System.out.println("token invalid");
		}
		filterChain.doFilter(request, response);
	}

	
}
