package com.github.micro.product.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.micro.common.entity.AuditEntity;
@Entity
@Table(name="t_product")
public class Product extends AuditEntity
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="product_code")
	private String productCode;
	@Column(name="product_name")
	private String productName;
	@Column(name="product_desc")
	private String productDesc;
	
	
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getProductCode()
	{
		return productCode;
	}
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	public String getProductDesc()
	{
		return productDesc;
	}
	public void setProductDesc(String productDesc)
	{
		this.productDesc = productDesc;
	}
	
	
	
	
}
