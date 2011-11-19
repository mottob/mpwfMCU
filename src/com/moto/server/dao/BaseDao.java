package com.moto.server.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDao {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public JdbcTemplate getJdbcTemplate()
	{
		return new JdbcTemplate(this.dataSource);
	}
}
