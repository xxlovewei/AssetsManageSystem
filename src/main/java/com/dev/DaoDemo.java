package com.dev;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.SpringOracleDao;
import com.dt.module.db.DB;

/**
 * @author: algernonking
 * @date: Oct 10, 2019 1:33:56 PM
 * @Description: TODO
 */
public class DaoDemo {

	public static DataSource dataSource() throws Exception {
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl("jdbc:mysql://39.105.191.22:6003/dt?useUnicode=true&characterEncoding=utf8&useSSL=false");
		datasource.setUsername("root");
		datasource.setPassword("root_pwd");
		//datasource.setDriverClassName(this.driverClassName);

		datasource.setInitialSize(10);
		datasource.setMinIdle(10);
		datasource.setMaxActive(20);
		datasource.setMaxWait(20);
//	        datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
//	        datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
//	        datasource.setValidationQuery(this.validationQuery);
//	        datasource.setTestWhileIdle(this.testWhileIdle);
//	        datasource.setTestOnBorrow(this.testOnBorrow);
//	        datasource.setTestOnReturn(this.testOnReturn);
//	        datasource.setPoolPreparedStatements(this.poolPreparedStatements);
//	        datasource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
//	        datasource.setDefaultAutoCommit(this.defaultAutoCommit);
//	        datasource.setFilters(this.filters);
//	        datasource.setConnectionProperties(this.connectionProperties);
		return datasource;
	}

 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DB dao = new DB();
		try {
			dao.setDataSource(dataSource());
			RcdSet rs= dao.query("select * from   sys_files");
			System.out.println(rs.toJsonArrayWithJsonObject());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
