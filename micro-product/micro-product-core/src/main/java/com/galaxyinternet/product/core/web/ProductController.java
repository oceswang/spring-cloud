package com.galaxyinternet.product.core.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.galaxyinternet.product.api.vo.ProductVO;
import com.galaxyinternet.product.core.entity.Product;
import com.galaxyinternet.product.core.service.ProductService;
import com.galaxyinternet.user.api.client.UserClient;

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
