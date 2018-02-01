package com.dt.module.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dt.dao.SpringOracleDao;
import com.dt.tool.lang.SpringContextUtil;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DB extends SpringOracleDao {
	private static Logger _log = LoggerFactory.getLogger(DB.class);

	public static DB instance() {
		return SpringContextUtil.getBean(DB.class);
	}
	@Resource(name = "db")
	public void setDataSource(DataSource dataSource) {
		_log.info("Oracle db setDataSource");
		super.setDataSource(dataSource);
	}
}
