package com.sjyttkl.spring.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjyttkl.spring.bean.User;

/**�û�Dao*/
@Repository
public class UserDao2
{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 /**ͨ���û�ID��ȡ�û���Ϣ*/
	public User get(Integer id)
	{
		String sql = "select * from user where id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper,id);
		return user;
	}
	
	/**�޸��û����*/
	public void update(Integer id,Double price)
	{
		String sql = "update user set balance=balance-? where id = ?";
		jdbcTemplate.update(sql, new Object[]{price,id});
	}
	
}
