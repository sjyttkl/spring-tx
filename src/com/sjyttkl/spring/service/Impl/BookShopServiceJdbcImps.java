package com.sjyttkl.spring.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjyttkl.spring.Dao.BookDao;
import com.sjyttkl.spring.Dao.StoreDao;
import com.sjyttkl.spring.Dao.UserDao2;
import com.sjyttkl.spring.bean.Book;
import com.sjyttkl.spring.bean.Store;
import com.sjyttkl.spring.bean.User;
import com.sjyttkl.spring.exception.BookStockException;
import com.sjyttkl.spring.exception.UserBalanceException;
import com.sjyttkl.spring.service.BookShopService;

@Service("bookShopService")
public class BookShopServiceJdbcImps implements BookShopService
{

	@Autowired
	private UserDao2 userDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private StoreDao storeDao;

	 @Transactional
	 /**购买图书方法*/
	public void purchase(Integer id,String sn)
	{
		//1:查收出图库存信息
		Store store = storeDao.get(sn);
		if(store.getStock()<=0)
		{
			throw new BookStockException("图书库存不足 "+store);
		}
		
		
		//4，修改库存,放到这里就必须使用事务操作了，不然会出现问题的
		storeDao.update(sn);
		
		//2:查询图书信息
		
		Book book = bookDao.get(sn);
		
		
		//3:查询用户信息
		User user = userDao.get(id);
		if(user.getBalance()<book.getPrice())
		{
			throw new  UserBalanceException("用户余额不足："+user);
		}
		
//		//4，修改库存
//		storeDao.update(sn);
		
		//5,修改余额
		
		userDao.update(id,book.getPrice());
	}

}





















