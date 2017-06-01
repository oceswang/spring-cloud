package com.galaxyinternet.user.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.user.core.dao.RoleDAO;
import com.galaxyinternet.user.core.entity.Role;

@Service
public class RoleService {

	@Autowired
	RoleDAO dao;
	@Transactional
	public Role save(Role role)
	{
		return dao.save(role);
	}
}
