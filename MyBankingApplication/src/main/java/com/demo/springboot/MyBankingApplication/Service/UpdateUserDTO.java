package com.demo.springboot.MyBankingApplication.Service;

public class UpdateUserDTO {
	private String mobileNum;
	private String email;
	private int customerId;
	public UpdateUserDTO(String mobileNum, String email, int customerId) {
		super();
		this.mobileNum = mobileNum;
		this.email = email;
		this.customerId = customerId;
	}
	public UpdateUserDTO() {
		super();
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public String getEmail() {
		return email;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	
	
	
}
