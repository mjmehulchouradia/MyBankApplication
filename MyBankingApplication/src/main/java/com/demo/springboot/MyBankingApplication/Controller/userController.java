package com.demo.springboot.MyBankingApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.MyBankingApplication.Model.userModel;
import com.demo.springboot.MyBankingApplication.Repositories.UserDetailsRepository;
import com.demo.springboot.MyBankingApplication.SecurityConfigurtion.LoginDTO;
import com.demo.springboot.MyBankingApplication.Service.UpdateUserDTO;
import com.demo.springboot.MyBankingApplication.Service.UserDetailsService;
import com.demo.springboot.MyBankingApplication.UtilPackage.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
public class userController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private JwtUtil jwtUtil;

@GetMapping("/{name}")
public userModel getUserDetails(@PathVariable String name)
{
	
	userModel user=userDetailsService.getUserDetails(name);
	return user;
	
}

@GetMapping("/userdetails")
public userModel getUser(@RequestBody String token)
{
	String jwt = token.substring(7);
    String username = jwtUtil.getUserNameFromToken(jwt);
    
    return userDetailsService.getUserDetails(username);
}

@PostMapping("/register")
public userModel createUser(@Valid @RequestBody userModel userModel){
	System.out.println("Entered controller");
	
	return userDetailsService.saveUser(userModel);
}

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO)
{
//	return new ResponseEntity<userModel>(userDetailsService.loginService(loginDTO),HttpStatus.OK);

	String token=userDetailsService.login(loginDTO);
	
	return new ResponseEntity<String>(token,HttpStatus.OK);
	
}

@PutMapping("/update")
public UpdateUserDTO updateUser(@RequestBody UpdateUserDTO updateUserDTO)
{
	UpdateUserDTO update=userDetailsService.updateUser(updateUserDTO);
	
	return update;
}
	
}
