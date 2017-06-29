package com.galaxyinternet.product.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.product.core.dao.ProductDAO;
import com.galaxyinternet.product.core.entity.Product;

@Service
public class ProductService
{
	@Autowired
	private ProductDAO dao;
	
	@Transactional(readOnly=true)
	public Product getById(Long id)
	{
		return dao.findOne(id);
	}
	
	
}
