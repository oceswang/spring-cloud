package com.galaxyinternet.product.api.vo;

public class ProductVO
{
	private Long id;
	private String productCode;
	private String productName;
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
