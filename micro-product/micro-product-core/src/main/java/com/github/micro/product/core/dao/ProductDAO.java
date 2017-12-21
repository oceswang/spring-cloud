package com.github.micro.product.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.micro.product.core.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long>
{

}
