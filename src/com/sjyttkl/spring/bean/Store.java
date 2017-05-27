package com.sjyttkl.spring.bean;

/** ≤÷ø‚¿‡ */
public class Store
{

	private String sn;
	private Integer stock;

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public Integer getStock()
	{
		return stock;
	}

	public void setStock(Integer stock)
	{
		this.stock = stock;
	}

	@Override
	public String toString()
	{
		return "Store [sn=" + sn + ", stock=" + stock + "]";
	}

}