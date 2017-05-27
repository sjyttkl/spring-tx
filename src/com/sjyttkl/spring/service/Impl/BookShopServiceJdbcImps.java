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
	 /**����ͼ�鷽��*/
	public void purchase(Integer id,String sn)
	{
		//1:���ճ�ͼ�����Ϣ
		Store store = storeDao.get(sn);
		if(store.getStock()<=0)
		{
			throw new BookStockException("ͼ���治�� "+store);
		}
		
		
		//4���޸Ŀ��,�ŵ�����ͱ���ʹ����������ˣ���Ȼ����������
		storeDao.update(sn);
		
		//2:��ѯͼ����Ϣ
		
		Book book = bookDao.get(sn);
		
		
		//3:��ѯ�û���Ϣ
		User user = userDao.get(id);
		if(user.getBalance()<book.getPrice())
		{
			throw new  UserBalanceException("�û����㣺"+user);
		}
		
//		//4���޸Ŀ��
//		storeDao.update(sn);
		
		//5,�޸����
		
		userDao.update(id,book.getPrice());
	}

}





















