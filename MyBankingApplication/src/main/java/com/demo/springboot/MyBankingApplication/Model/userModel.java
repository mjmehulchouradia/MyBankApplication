package com.demo.springboot.MyBankingApplication.Model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="UserDetails")
public class userModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	@Size(min = 5,message="First name should be more than 5")
	private String firstname;
	@NotBlank
	private String lastname;
	@NotNull
	private String role;
	@Pattern(regexp = "^[0-9]{10}$", message = "Employee mobile number must be a 10-digit number")
	private String mobileNum;
	@Email
	private String email;
	@Size(min=5)
	private String username;
	@Size(min=6)
	private String password;
	
	@OneToMany(mappedBy = "usermodel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountModel> accounts;
	
	public userModel(int customerId, String firstname, String lastname, String role, String mobileNum, String email,
			String username, String password) {
		super();
		this.customerId = customerId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.mobileNum = mobileNum;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public userModel() {
	
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getRole() {
		return role;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "userModel [customerId=" + customerId + ", firstname=" + firstname + ", lastname=" + lastname + ", role="
				+ role + ", mobileNum=" + mobileNum + ", email=" + email + ", username=" + username + ", password="
				+ password + ", accounts=" + accounts + "]";
	}
	
	
	
}
