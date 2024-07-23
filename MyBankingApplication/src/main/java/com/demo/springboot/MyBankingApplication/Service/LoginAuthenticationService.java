package com.demo.springboot.MyBankingApplication.Service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springboot.MyBankingApplication.Model.userModel;
import com.demo.springboot.MyBankingApplication.Repositories.UserDetailsRepository;

@Service
public class LoginAuthenticationService implements AuthenticationProvider {
 
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName=authentication.getName();
		String password=authentication.getCredentials().toString();	
		
		
		userModel user=userDetailsRepository.findByUsername(userName);
		
		
		
		if(user!=null && encoder.matches(password, user.getPassword())) {
			
			System.out.println("Validation");
			
			HashSet<GrantedAuthority> set=new HashSet<>();
			
			set.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
			
			System.out.println("Added");
		
		return new UsernamePasswordAuthenticationToken(userName, password,set);
		
		}
		else {
			System.out.println("Error");
		 throw new BadCredentialsException("Invalid username or password");
		}
	
		

	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
