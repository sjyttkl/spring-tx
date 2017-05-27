package com.sjyttkl.spring.bean;

public class Book
{

	private String sn;
	private String name;
	private Double price;

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Double getPrice()
	{
		return price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public String toString()
	{
		return "Book [sn=" + sn + ", name=" + name + ", price=" + price + "]";
	}

}