package com.galaxyinternet.account.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.galaxyinternet.account.entity.Account;

public interface AccountDAO extends PagingAndSortingRepository<Account, Long> {
	

}
