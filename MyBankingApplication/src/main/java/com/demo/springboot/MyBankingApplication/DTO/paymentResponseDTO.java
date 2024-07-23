package com.demo.springboot.MyBankingApplication.DTO;

public class paymentResponseDTO {

	private String benificiaryName;
	private int benificiaryAccountNumber;
	private int customerId;
	public paymentResponseDTO() {
		super();
	}
	public paymentResponseDTO(String benificiaryName, int benificiaryAccountNumber, int customerId) {
		super();
		this.benificiaryName = benificiaryName;
		this.benificiaryAccountNumber = benificiaryAccountNumber;
		this.customerId = customerId;
	}
	public String getBenificiaryName() {
		return benificiaryName;
	}
	public int getBenificiaryAccountNumber() {
		return benificiaryAccountNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setBenificiaryName(String benificiaryName) {
		this.benificiaryName = benificiaryName;
	}
	public void setBenificiaryAccountNumber(int benificiaryAccountNumber) {
		this.benificiaryAccountNumber = benificiaryAccountNumber;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
	
}
