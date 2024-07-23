package com.demo.springboot.MyBankingApplication.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springboot.MyBankingApplication.Model.AccountModel;
import com.demo.springboot.MyBankingApplication.Model.userModel;
import com.demo.springboot.MyBankingApplication.Repositories.AccountRepository;
import com.demo.springboot.MyBankingApplication.Repositories.UserDetailsRepository;

@Service
public class accountServices {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public AccountModel saveAccount(AccountModel accountModel) {
	

			userModel user=userDetailsRepository.findByCustomerId(accountModel.getCustid());
			
			accountModel.setUsermodel(user);
			
			return accountRepository.save(accountModel);
			
		
	}

	public Set<AccountModel> getAccountDetails(int id) {
		
		Set<AccountModel> accountSet=accountRepository.findByusermodel_CustomerId(id);
		
		return accountSet;
	}

}
