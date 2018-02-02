package com.dt.module.base.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dt.core.shiro.SimpleFilterChainDefinitionsService;
import com.dt.module.base.schedule.service.ScheduleMangerService;
//import com.dt.module.base.schedule.service.ScheduleMangerService;
import com.dt.tool.lang.SpringContextUtil;
import com.dt.tool.util.ToolUtil;

/**
 * spring容器初始化完成事件 Spring框架加载完成后会publishContextRefreshedEvent事件
 * 创建ContextRefreshedEvent事件监听类
 */
@Component("myServletContextListener")
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger _log = LoggerFactory.getLogger(ApplicationContextListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// root application context
		if (null == event.getApplicationContext().getParent()) {
			SpringContextUtil.getApplicationContext();
			_log.info(">>>>> spring初始化完毕 <<<<<");
			_log.info("更新Chain");
			SimpleFilterChainDefinitionsService.me().updatePermission();
			try {
				InputStream in = ApplicationContextListener.class.getClassLoader()
						.getResourceAsStream("sys.properties");
				Properties ps = new Properties();
				ps.load(in);
				String initjob = ps.getProperty("job.init");
				if (ToolUtil.isNotEmpty(initjob) && initjob.toLowerCase().equals("true")) {
					_log.info("Job Start.");
					ScheduleMangerService scheduleMangerService = ScheduleMangerService.me();
					if (scheduleMangerService.scheduleisShutdown()) {
						scheduleMangerService.scheduleStart();
						scheduleMangerService.jobInitLoadFromDb();
					}
				} else {
					_log.info("Job Not Start.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				_log.info("读取sys.properties发生错误.");
				e.printStackTrace();
			}

		}
	}
}
