package com.demo.springboot.MyBankingApplication.Model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Transactions")
public class transactionsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	private Double amount;
	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date transactionDate;
	
	private String mode;
	
	private String type;
	
	private int benificiaryId;
	
	private int accountNum;
	
//	private int accountNum;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountNumber", referencedColumnName = "accountNumber")
	@JsonIgnore
	private AccountModel accountModel;
	

	public transactionsModel(int transactionId, Double amount, Date transactionDate, String mode,
			String type, int benificiaryId,int accountNum) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.mode = mode;
		this.type = type;
		this.benificiaryId = benificiaryId;
		this.accountNum=accountNum;
	}

	public transactionsModel() {
	}

	public int getTransactionId() {
		return transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public String getMode() {
		return mode;
	}

	public String getType() {
		return type;
	}

	public int getBenificiaryId() {
		return benificiaryId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBenificiaryId(int benificiaryId) {
		this.benificiaryId = benificiaryId;
	}

	public AccountModel getAccountModel() {
		return accountModel;
	}

	public void setAccountModel(AccountModel accountModel) {
		this.accountModel = accountModel;
	}
	
	
}
