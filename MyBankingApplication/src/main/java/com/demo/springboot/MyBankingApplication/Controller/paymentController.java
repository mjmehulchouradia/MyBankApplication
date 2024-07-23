package com.demo.springboot.MyBankingApplication.Controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.MyBankingApplication.DTO.paymentResponseDTO;
import com.demo.springboot.MyBankingApplication.Model.transactionsModel;
import com.demo.springboot.MyBankingApplication.Service.paymentsService;


@RestController
@RequestMapping("/payments/")
@CrossOrigin(origins = "http://localhost:5173/")
public class paymentController {
		
	@Autowired
	private paymentsService paymentsService;

	@GetMapping("/{id}")
	public ResponseEntity<List<paymentResponseDTO>> getBenificiaryDetails(@PathVariable int id) throws BadRequestException
	{
		List<paymentResponseDTO> result=paymentsService.getBenificiaryList(id);
		
		if(result.size()!=0)
		{
			return new ResponseEntity<>(result,HttpStatus.OK);
		}
		else
		{
			throw new BadRequestException("No Benificiary Exist");
		}
		
	}
	
	@PostMapping("/createTransaction")
	public String paymentMethod(@RequestBody transactionsModel transactionsModel)
	{
		
		System.out.println("Tranx Controller");
		paymentsService.createTransaction(transactionsModel);
		
		return "Transaction Successfully done";
		
	}
		
	
}
