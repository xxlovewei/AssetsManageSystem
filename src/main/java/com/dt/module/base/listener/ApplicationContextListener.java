package com.dt.module.base.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dt.core.common.base.BaseConstants;
import com.dt.core.shiro.service.SimpleFilterChainDefinitionsService;
import com.dt.core.tool.lang.SpringContextUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.schedule.service.ScheduleMangerService;

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
			try {
				InputStream in = ApplicationContextListener.class.getClassLoader()
						.getResourceAsStream("config.properties");
				Properties ps = new Properties();
				ps.load(in);
				// 判断shiro
				String shiroenable= ps.getProperty("shiro.enable");
				if (ToolUtil.isNotEmpty(shiroenable) && "true".equals(shiroenable.toLowerCase())) {
					BaseConstants.shiroenable="true";
				}else{
					BaseConstants.shiroenable="false";
				}
				// 判断shiroupdateperm
				String updateperm = ps.getProperty("shiro.updateperm");
				if (ToolUtil.isNotEmpty(updateperm) && "true".equals(updateperm.toLowerCase())) {
					_log.info("更新Shiro Chain");
					SimpleFilterChainDefinitionsService.me().updatePermission();
				} else {
					_log.info("不更新,无Shiro模块");
				}
				// 判断Job
				String initjob = ps.getProperty("job.enable");
				if (ToolUtil.isNotEmpty(initjob) && "true".equals(initjob.toLowerCase())) {
					_log.info("Job Start.");
					ScheduleMangerService scheduleMangerService = ScheduleMangerService.me();
					scheduleMangerService.scheduleStart();
					scheduleMangerService.jobInitLoadFromDb();
				} else {
					_log.info("Job Not Start.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				_log.info("读取config.properties发生错误.");
				e.printStackTrace();
			}

		}
	}
}
