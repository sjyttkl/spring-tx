package com.sjyttkl.spring.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjyttkl.spring.bean.Book;
//数据访问层
/**图书Dao*/
@Repository
public class BookDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** 通过图书编号获取图书信息 */
	public Book get(String sn)
	{
		String sql = "select * from book where sn = ?";
		RowMapper<Book> rowMapper = new BeanPropertyRowMapper<Book>(Book.class);
		Book book = jdbcTemplate.queryForObject(sql, rowMapper, sn);
		return book;
	}
}
