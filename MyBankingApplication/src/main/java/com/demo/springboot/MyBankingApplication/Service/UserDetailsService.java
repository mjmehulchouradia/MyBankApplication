package com.demo.springboot.MyBankingApplication.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springboot.MyBankingApplication.Model.userModel;
import com.demo.springboot.MyBankingApplication.Repositories.UserDetailsRepository;
import com.demo.springboot.MyBankingApplication.SecurityConfigurtion.LoginDTO;
import com.demo.springboot.MyBankingApplication.UtilPackage.JwtUtil;

@Service
public class UserDetailsService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private LoginAuthenticationService authenticationService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtil jwtUtil;

	
	public userModel saveUser(userModel user)
	{

		user.setPassword(encoder.encode(user.getPassword()));
		return userDetailsRepository.save(user);
	}

	
	
	public String login(LoginDTO loginDTO)
	{
Authentication auth	=authenticationService.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		
		if(auth.isAuthenticated())
		{
			String jwt=jwtUtil.generateToken(auth);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			return jwt;
		}
		else {
		
		return "Invalid Credentials";
		}
	}
	
	
	public userModel loginService(LoginDTO loginDTO)
	{
Authentication auth	=authenticationService.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		
		if(auth.isAuthenticated())
		{
			
			userModel user= userDetailsRepository.findByUsername(loginDTO.getUsername());
			user.setPassword("");
			return user;
		}
		else {
		
			throw new BadCredentialsException("User Doesn't exist");
		}
	}
	
	
	
	public userModel getUserDetails(String name)
	{
		userModel user=userDetailsRepository.findByUsername(name);
		
		if(user!=null)
		{
			user.setPassword("");
		return user;
		}
		else
			throw new BadCredentialsException("User Doesn't exist");
	}



	public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
		 Optional<userModel> user=userDetailsRepository.findById(updateUserDTO.getCustomerId());
		 
		 
		 if(user.isPresent())
		 {
		
		  userModel u= user.get();
		  u.setMobileNum(updateUserDTO.getMobileNum());
		  u.setEmail(updateUserDTO.getEmail());
		  
		  
		  u=userDetailsRepository.save(u);
			
			UpdateUserDTO DTO=new UpdateUserDTO(u.getMobileNum(),u.getEmail(),u.getCustomerId());
			
			return DTO;
		 }
		 else
			 throw new BadCredentialsException("Invalid User");
		
	}
	 
}
