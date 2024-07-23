package com.demo.springboot.MyBankingApplication.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class ErrorDTO {
	
	List<String> errorMessage=new ArrayList<>();

	public ErrorDTO(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	

}
