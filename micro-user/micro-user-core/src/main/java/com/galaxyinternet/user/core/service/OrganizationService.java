package com.galaxyinternet.user.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.user.core.dao.OrganizationDAO;
import com.galaxyinternet.user.core.entity.Organization;

@Service
public class OrganizationService {

	@Autowired
	OrganizationDAO dao;
	@Transactional
	public Organization save(Organization org)
	{
		return dao.save(org);
	}
}
