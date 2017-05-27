package com.sjyttkl.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class Main
{
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate = null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	{
		// 启动IoC容器
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取IoC容器中JdbcTemplate实例
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
	}

	// a、通过update插入数据
	@Test
	public void testInsert()
	{
		String sql = "insert into user(id,name,age) values(?,?,?)";
		int count = jdbcTemplate.update(sql, new Object[]
		{ 16, "小冬3", 45 });
		System.out.println("插入了几个： " + count);
	}

	// c、通过update删除数据
	@Test
	public void testdelete()
	{
		String sql = "delete from user where id = ?";
		int count = jdbcTemplate.update(sql, 16);
		System.out.println("删除了几个： " + count);
	}

	// c、通过update更新数据
	@Test
	public void testupdate()
	{
		String sql = "update user set name=?,age=? where id=?";
		int count = jdbcTemplate.update(sql, new Object[]
		{ "中国人民", 809, 4 });
		System.out.println("更新了几个个： " + count);
	}

	// 2、batchUpdate()批量插入、更新和删除方法

	@Test
	public void testbatchUpdate()
	{
		// batchUpdate方法第二参数是一个元素为Object[]数组类型的List集合
		String sql = "insert into user(id ,name,age)values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[]
		{ 17, "宋冬冬", 32 });
		batchArgs.add(new Object[]
		{ 18, "宋冬冬2", 89 });
		batchArgs.add(new Object[]
		{ 19, "宋冬冬3", 100 });

		int[] count = jdbcTemplate.batchUpdate(sql, batchArgs);
		System.out.println(Arrays.toString(count));
	}

	// 读取单个对象
	@Test
	public void testObejct()
	{

		// 【注意】：1、使用BeanProperytRowMapper要求sql数据查询出来的列和实体属性需要一一对应。如果数据中列明和属性名不一致，在sql语句中需要用as重新取一个别名

		// 2、使用JdbcTemplate对象不能获取关联对象
		String sql = "select id,name,age from user where id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, 4);
		System.out.println(user);
	}

	// b、读取多个对象
	@Test
	public void testManyObject()
	{
		String sql = "select id,name,age from user ";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users = jdbcTemplate.query(sql, rowMapper);
		System.out.println(users.toString());
	}

	// c、获取某个记录某列或者count、avg、sum等函数返回唯一值
	@Test
	public void testCount()
	{
		String sql="select count(*) from user";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(count);
	}
	
	@Test
	public void testUserDao()
	{
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		System.out.println(userDao.get(5));
	}
	
	
	//方式一：
	@Test
	public void testNameInsert()
	{
		//为变量名称前面加上冒号
		String sql = "insert into user(id,name,age) values(:id,:name,:age)";
		 //定义map集合，其参数名称为sql语句中变量的名称
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 20);
		paramMap.put("name", "yanshu3");
		paramMap.put("age", 15);
		int count= namedParameterJdbcTemplate.update(sql, paramMap);
		System.out.println("namedParameterJdbc__update : "+count);
		
	}
	
	//方式二：
		@Test
		public void testNameInsert2()
		{
			//为变量名称前面加上冒号
			String sql = "insert into user(id,name,age) values(:id,:name,:age)";
			
			//定义个实体类
			User user = new User();
			user.setId(21);
			user.setName("晏姝8");
			user.setAge(10);
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
			int count  = namedParameterJdbcTemplate.update(sql, paramSource);
			System.out.println("namedParameterJdbc__update : "+count);
			
			
			
			
			
			
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
