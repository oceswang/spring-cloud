package com.galaxyinternet.user.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.user.core.dao.ResourceDAO;
import com.galaxyinternet.user.core.entity.Resource;

@Service
public class ResourceService {

	@Autowired
	ResourceDAO dao;
	@Transactional
	public Resource save(Resource resource)
	{
		return dao.save(resource);
	}
}
