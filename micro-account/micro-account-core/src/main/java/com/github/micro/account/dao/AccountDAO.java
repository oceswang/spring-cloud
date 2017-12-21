package com.github.micro.account.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.micro.account.entity.Account;

public interface AccountDAO extends PagingAndSortingRepository<Account, Long> {
	

}
