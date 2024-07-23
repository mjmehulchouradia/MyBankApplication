package com.demo.springboot.MyBankingApplication.Repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springboot.MyBankingApplication.Model.AccountModel;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Integer> {

	public Set<AccountModel> findByusermodel_CustomerId(int customerId);
	
}
