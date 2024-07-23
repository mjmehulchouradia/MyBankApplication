package com.demo.springboot.MyBankingApplication.SecurityConfigurtion;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.springboot.MyBankingApplication.Model.userModel;
import com.demo.springboot.MyBankingApplication.Repositories.UserDetailsRepository;
import com.demo.springboot.MyBankingApplication.UtilPackage.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  String authorizationHeader = request.getHeader("Authorization");
	        String username = null;
	        String jwt = null;
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            jwt = authorizationHeader.substring(7);
	            username = jwtUtil.getUserNameFromToken(jwt);
	        }
	        
	        if(username!=null)
	        {
	        	if(jwtUtil.validateToken(jwt))
	        	{
	        		userModel user=userDetailsRepository.findByUsername(username);
	        		
	        		HashSet<GrantedAuthority> set=new HashSet<>();
	    			
	    			set.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));

	        		UsernamePasswordAuthenticationToken auth=new 
	        				UsernamePasswordAuthenticationToken(user.getUsername(), null,set);
	        		
	        		auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	        		SecurityContextHolder.getContext().setAuthentication(auth);
	        	}
	        	
	        }
	        
	 	   filterChain.doFilter(request, response);

	}

}
