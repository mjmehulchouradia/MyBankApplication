package com.demo.springboot.MyBankingApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springboot.MyBankingApplication.Model.userModel;

@Repository
public interface UserDetailsRepository extends JpaRepository<userModel,Integer> {
	public userModel findByEmail(String email);
	public userModel findByCustomerId(int custId);
	public userModel findByUsername(String username);
}
