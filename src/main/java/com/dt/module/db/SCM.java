package com.dt.module.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.core.dao.SpringOracleDao;
import com.dt.core.tool.lang.SpringContextUtil;
//
//@Service
//@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SCM extends SpringOracleDao {
	private static Logger _log = LoggerFactory.getLogger(SCM.class);

	public static SCM instance() {
		return SpringContextUtil.getBean(SCM.class);
	}
	@Resource(name = "scm")
	public void setDataSource(DataSource dataSource) {
		_log.info("Oracle scm setDataSource");
		super.setDataSource(dataSource);
	} 
}
