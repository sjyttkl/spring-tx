package com.sjyttkl.spring.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjyttkl.spring.bean.Store;

/** Õº È≤÷ø‚Dao */
@Repository
public class StoreDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Store get(String sn)
	{
		String sql = "select * from store where sn = ?";
		RowMapper<Store> rowMapper = new BeanPropertyRowMapper<Store>(Store.class);
		Store store = jdbcTemplate.queryForObject(sql, rowMapper, sn);
		return store;
	}

	/** Õ®π˝Õº È±‡∫≈£¨–ﬁ∏ƒÕº Èø‚¥Ê ø‚¥Ê=µ±«∞ø‚¥Ê-1 */
	public void update(String sn)
	{
		String sql = "update store set stock = stock -1 where sn=?";
		jdbcTemplate.update(sql, sn);
	}
}
