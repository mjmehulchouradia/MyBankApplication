package com.demo.springboot.MyBankingApplication.Model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="Accounts")
public class AccountModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNumber;
	
	@Transient
	private int custid;
	
	private String accountType;
     
	private Double balance;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
	@JsonIgnore
	private userModel usermodel;
	
	@OneToMany(mappedBy = "accountModel",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<transactionsModel> transactions;
	
	public AccountModel(int accountNumber, String accountType, Double balance,
			userModel usermodel,int custid) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.usermodel = usermodel;
		this.custid=custid;
	}

	public AccountModel() {
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public Double getBalance() {
		return balance;
	}


	public userModel getUsermodel() {
		return usermodel;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setUsermodel(userModel usermodel) {
		this.usermodel = usermodel;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}


	
}
