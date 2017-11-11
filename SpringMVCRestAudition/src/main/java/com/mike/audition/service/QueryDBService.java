package com.mike.audition.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mike.audition.dao.AccountDao;
import com.mike.audition.model.LookUpForm;

@Service
public class QueryDBService {
	@Autowired
	private AccountDao Querydao;

	/**
	 * Description: get accounts by id
	 * 
	 * @param id do an account lookup
	 * @return
	 */
	public LookUpForm getAccountById(String account){
		
		LookUpForm lookUpResult = Querydao.getAccount(account);
		if (null == lookUpResult ){
			 return new LookUpForm(false, UUID.randomUUID().toString());
		}
		return 	lookUpResult;
	}

	
	/**
	 * Description: get all accounts
	 * @return
	 */
	public Map<String, LookUpForm> getAllAccounts() {
		return Querydao.getAllResults();
	}
}
