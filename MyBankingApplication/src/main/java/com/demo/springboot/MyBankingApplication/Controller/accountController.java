package com.demo.springboot.MyBankingApplication.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.MyBankingApplication.Model.AccountModel;
import com.demo.springboot.MyBankingApplication.Repositories.UserDetailsRepository;
import com.demo.springboot.MyBankingApplication.Service.accountServices;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:5173/")
public class accountController {
	
    @Autowired	
	private accountServices accountServices;
    
	@Autowired
	private UserDetailsRepository userDetailsRepository;
    
@PostMapping("/create")
public String createAccount(@RequestBody AccountModel accountModel)
{
	
	accountServices.saveAccount(accountModel);
	 
	 return "Account Created";
}

@GetMapping("/{id}")
public Set<AccountModel> getAccount(@PathVariable int id)
{
	Set<AccountModel> accountModel=accountServices.getAccountDetails(id);
	return accountModel;
}

}
