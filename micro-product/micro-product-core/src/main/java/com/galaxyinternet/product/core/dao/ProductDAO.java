package com.galaxyinternet.product.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxyinternet.product.core.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long>
{

}
