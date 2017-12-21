package com.github.micro.product.core.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.micro.product.api.vo.ProductVO;
import com.github.micro.product.core.entity.Product;
import com.github.micro.product.core.service.ProductService;
import com.github.micro.user.api.client.UserClient;

@RestController
public class ProductController
{
	@Autowired
	private UserClient userClient;
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ProductVO getById(Long id)
	{
		System.out.println("========= call use client================");
		userClient.getById(id);
		Product po = productService.getById(id);
		ProductVO vo = new ProductVO();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}
}
