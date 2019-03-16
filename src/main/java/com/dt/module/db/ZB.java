package com.dt.module.db;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.dt.core.dao.SpringOracleDao;
import com.dt.core.tool.lang.SpringContextUtil;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ZB extends SpringOracleDao {
	private static Logger _log = LoggerFactory.getLogger(ZB.class);

	public static ZB instance() {
		return SpringContextUtil.getBean(ZB.class);
	}

	private String dbname = "zb";

	@Resource(name = "zb")
	public void setDataSource(DataSource dataSource) {
		_log.info(getDBType() + " " + dbname + " setDataSource");
		super.setDataSource(dataSource);
	}
}
