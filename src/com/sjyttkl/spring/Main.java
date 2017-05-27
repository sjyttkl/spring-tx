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
		// ����IoC����
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ��ȡIoC������JdbcTemplateʵ��
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
	}

	// a��ͨ��update��������
	@Test
	public void testInsert()
	{
		String sql = "insert into user(id,name,age) values(?,?,?)";
		int count = jdbcTemplate.update(sql, new Object[]
		{ 16, "С��3", 45 });
		System.out.println("�����˼����� " + count);
	}

	// c��ͨ��updateɾ������
	@Test
	public void testdelete()
	{
		String sql = "delete from user where id = ?";
		int count = jdbcTemplate.update(sql, 16);
		System.out.println("ɾ���˼����� " + count);
	}

	// c��ͨ��update��������
	@Test
	public void testupdate()
	{
		String sql = "update user set name=?,age=? where id=?";
		int count = jdbcTemplate.update(sql, new Object[]
		{ "�й�����", 809, 4 });
		System.out.println("�����˼������� " + count);
	}

	// 2��batchUpdate()�������롢���º�ɾ������

	@Test
	public void testbatchUpdate()
	{
		// batchUpdate�����ڶ�������һ��Ԫ��ΪObject[]�������͵�List����
		String sql = "insert into user(id ,name,age)values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[]
		{ 17, "�ζ���", 32 });
		batchArgs.add(new Object[]
		{ 18, "�ζ���2", 89 });
		batchArgs.add(new Object[]
		{ 19, "�ζ���3", 100 });

		int[] count = jdbcTemplate.batchUpdate(sql, batchArgs);
		System.out.println(Arrays.toString(count));
	}

	// ��ȡ��������
	@Test
	public void testObejct()
	{

		// ��ע�⡿��1��ʹ��BeanProperytRowMapperҪ��sql���ݲ�ѯ�������к�ʵ��������Ҫһһ��Ӧ�������������������������һ�£���sql�������Ҫ��as����ȡһ������

		// 2��ʹ��JdbcTemplate�����ܻ�ȡ��������
		String sql = "select id,name,age from user where id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, 4);
		System.out.println(user);
	}

	// b����ȡ�������
	@Test
	public void testManyObject()
	{
		String sql = "select id,name,age from user ";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users = jdbcTemplate.query(sql, rowMapper);
		System.out.println(users.toString());
	}

	// c����ȡĳ����¼ĳ�л���count��avg��sum�Ⱥ�������Ψһֵ
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
	
	
	//��ʽһ��
	@Test
	public void testNameInsert()
	{
		//Ϊ��������ǰ�����ð��
		String sql = "insert into user(id,name,age) values(:id,:name,:age)";
		 //����map���ϣ����������Ϊsql����б���������
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 20);
		paramMap.put("name", "yanshu3");
		paramMap.put("age", 15);
		int count= namedParameterJdbcTemplate.update(sql, paramMap);
		System.out.println("namedParameterJdbc__update : "+count);
		
	}
	
	//��ʽ����
		@Test
		public void testNameInsert2()
		{
			//Ϊ��������ǰ�����ð��
			String sql = "insert into user(id,name,age) values(:id,:name,:age)";
			
			//�����ʵ����
			User user = new User();
			user.setId(21);
			user.setName("���8");
			user.setAge(10);
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
			int count  = namedParameterJdbcTemplate.update(sql, paramSource);
			System.out.println("namedParameterJdbc__update : "+count);
			
			
			
			
			
			
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
