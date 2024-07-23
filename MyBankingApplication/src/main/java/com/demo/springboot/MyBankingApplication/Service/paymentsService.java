package com.demo.springboot.MyBankingApplication.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springboot.MyBankingApplication.DTO.paymentResponseDTO;
import com.demo.springboot.MyBankingApplication.Model.transactionsModel;
import com.demo.springboot.MyBankingApplication.Model.userModel;
import com.demo.springboot.MyBankingApplication.Repositories.AccountRepository;
import com.demo.springboot.MyBankingApplication.Repositories.TransactionRepository;
import com.demo.springboot.MyBankingApplication.Repositories.UserDetailsRepository;

@Service
public class paymentsService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
    @Autowired
    private TransactionRepository transactionRepository;
    
    
	public List<paymentResponseDTO> getBenificiaryList(int customerId) {
		
		
		userModel u=userDetailsRepository.findByCustomerId(customerId);
		
		boolean flag=accountRepository.findByusermodel_CustomerId(customerId).isEmpty();
		
		
		if(!flag) {
		
		List<paymentResponseDTO> respList=accountRepository.findAll()
				.stream()
				.filter(t -> t.getUsermodel().getCustomerId()!=customerId)
				.map(resp -> new paymentResponseDTO(resp.getUsermodel().getFirstname(),resp.getAccountNumber(),resp.getUsermodel().getCustomerId()))
				.collect(Collectors.toList());
		
//		System.out.println(respList.size());
//		
//	      for(paymentResponseDTO res:respList)
//		{
//			System.out.println(res.getBenificiaryName()+" "+res.getBenificiaryAccountNumber()+" "+res.getCustomerId());
//		}
		
		
		return respList;
		}
		else
		{
			System.out.println("Not present");
			return new ArrayList<paymentResponseDTO>();
		}
		
		
	}


	public transactionsModel createTransaction(transactionsModel t) {
		// TODO Auto-generated method stub
		
		
		return transactionRepository.save(t);
		
	}
}
