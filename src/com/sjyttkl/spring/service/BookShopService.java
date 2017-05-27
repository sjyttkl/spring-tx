package com.sjyttkl.spring.service;

//逻辑业务层
public interface BookShopService
{

	/**
	 * 购买图书
	 * 
	 * @param userId
	 *            购买用户ID
	 * @param sn
	 *            图书编号
	 */
	void purchase(Integer userId, String sn);

}
