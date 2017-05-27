package com.sjyttkl.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sjyttkl.spring.service.BookShopService;

public class TestShopBook
{

	private ApplicationContext ctx;
	private BookShopService bookShopService;
	
	{
		ctx = new  ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopService = (BookShopService) ctx.getBean("bookShopService");
	}
	
	@Test
	public void purchase()
	{
		bookShopService.purchase(1, "1001");
	}

}
