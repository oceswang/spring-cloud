package com.galaxyinternet.account.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.account.entity.Account;

@Service
public class AccountService {

	@Autowired
	private AccountService service;
	@Transactional
	public Account init(Long userId)
	{
		Account account = new Account();
		account.setBalance(BigDecimal.ZERO);
		account.setUserId(userId);
		account.setCreatedTime(LocalDateTime.now());
		return account;
	}
	@Transactional
	public Account save(Account account)
	{
		return service.save(account);
	}
}
