package com.dt.module.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;
import com.dt.core.dao.SpringOracleDao;
import com.dt.core.tool.lang.SpringContextUtil;
import com.dt.module.base.listener.ApplicationContextListener;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SCM extends SpringOracleDao {
	private static Logger _log = LoggerFactory.getLogger(SCM.class);

	private String dbname = "scm";
	private String dbtype = "oracle";

	public static SCM instance() {
		return SpringContextUtil.getBean(SCM.class);
	}

	@Resource(name = "scm")
	public void setDataSource(DataSource dataSource) {
		_log.info(dbtype + " " + dbname + " setDataSource");
		super.setDataSource(dataSource);
	}

	/* @PostConstruct */
	public void setDataSource() {
		try {
			InputStream in = ApplicationContextListener.class.getClassLoader().getResourceAsStream("config.properties");
			Properties ps = new Properties();
			ps.load(in);
			String scm_enable = ps.getProperty("scm.enable", "false");
			String url = ps.getProperty("scm.url", "");
			String scm_username = ps.getProperty("scm.username", "");
			String scm_password = ps.getProperty("scm.password", "");
			if ("true".equals(scm_enable)) {
				_log.info(dbtype + " " + dbname + " setDataSource");
				DruidDataSource ds = new DruidDataSource();
				ds.setUrl(url);
				ds.setUsername(scm_username);
				ds.setPassword(scm_password);
				/* 配置初始化大小、最小、最大 */
				ds.setInitialSize(5);
				ds.setMinIdle(6);
				ds.setMaxActive(30);
				ds.setMaxWait(10000);
				/* 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
				ds.setTimeBetweenEvictionRunsMillis(60000);
				/* 配置一个连接在池中最小生存的时间，单位是毫秒 */
				ds.setMinEvictableIdleTimeMillis(300000);
				/* 申请连接的时候检测 */
				ds.setTestWhileIdle(true);
				/* 这里建议配置为TRUE，防止取到的连接不可用 */
				/* 申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能 */
				ds.setTestOnBorrow(true);
				/* 归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能 */
				ds.setTestOnReturn(false);
				/* 打开PSCache，并且指定每个连接上PSCache的大小 */
				ds.setPoolPreparedStatements(true);
				ds.setMaxPoolPreparedStatementPerConnectionSize(100);
				/* 这里配置提交方式，默认就是TRUE，可以不用配置 */
				ds.setDefaultAutoCommit(true);
				/* 验证连接有效与否的SQL，不同的数据配置不同 */
				ds.setValidationQuery("select 1 from dual");
				/* 打开 removeAbandoned 功能,处理连接泄露 */
				ds.setRemoveAbandoned(true);
				/*
				 * 1800 秒，也就是 30 分钟,datasource.getConnontion()
				 * 取得的连接必须在removeAbandonedTimeout这么多秒内调用close()
				 */
				ds.setRemoveAbandonedTimeout(3600);
				ds.setLogAbandoned(true);
				// 监控
				List<Filter> filters = new ArrayList<Filter>();
				StatFilter statFilter = new StatFilter();
				/* 开启合并sql */
				statFilter.setMergeSql(true);
				statFilter.setSlowSqlMillis(2000);
				statFilter.setLogSlowSql(true);
				filters.add(statFilter);
				Slf4jLogFilter logFilter = new Slf4jLogFilter();
				logFilter.setConnectionLogEnabled(false);
				logFilter.setStatementLogEnabled(true);
				logFilter.setResultSetLogEnabled(true);
				logFilter.setStatementExecutableSqlLogEnable(true);
				filters.add(logFilter);
				WallFilter wallFilter = new WallFilter();
				wallFilter.setDbType("oracle");
				filters.add(wallFilter);
				ds.setProxyFilters(filters);
				/* 配置事务 */
				// DataSourceTransactionManager tx = new
				// DataSourceTransactionManager();
				// tx.setDataSource(ds);
				setDataSource(ds);
			} else {
				_log.info(dbname + " datasource is not enabled.");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			_log.info("读取config.properties发生错误.");
			e.printStackTrace();
		}

	}
}
