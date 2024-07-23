package com.demo.springboot.MyBankingApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.springboot.MyBankingApplication.Model.transactionsModel;

@Repository
public interface TransactionRepository extends JpaRepository<transactionsModel, Integer> {

}
