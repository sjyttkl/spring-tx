package com.sjyttkl.spring.bean;

/**
 * @author xiaodong
 *
 */
public class User
{

	private Integer id;
	private String name;
	private Double balance;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Double getBalance()
	{
		return balance;
	}

	public void setBalance(Double balance)
	{
		this.balance = balance;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}

}