package com.dt.module.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "UTF-8")
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger _log = LoggerFactory.getLogger(ApplicationContextListener.class);

	@Value("${shiro.enable}")
	private String shiroenable;

	@Value("${shiro.updateperm}")
	private String updateperm;

	@Value("${job.enable}")
	private String jobenable;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// root application context

		if (null == event.getApplicationContext().getParent()) {
			SpringContextUtil.getApplicationContext();
			_log.info(">>>>> spring初始化完毕 <<<<<");
			// 判断shiro
			if (ToolUtil.isNotEmpty(shiroenable) && "true".equals(shiroenable.toLowerCase())) {
				BaseConstants.shiroenable = "true";
			} else {
				BaseConstants.shiroenable = "false";
			}
			// 判断shiroupdateperm
			if (ToolUtil.isNotEmpty(updateperm) && "true".equals(updateperm.toLowerCase())) {
				_log.info("更新Shiro Chain");
				SimpleFilterChainDefinitionsService.me().updatePermission();
			} else {
				_log.info("不更新,无Shiro模块");
			}
			// 判断Job
			if (ToolUtil.isNotEmpty(jobenable) && "true".equals(jobenable.toLowerCase())) {
				_log.info("Job Start.");
				ScheduleMangerService scheduleMangerService = ScheduleMangerService.me();
				scheduleMangerService.scheduleStart();
				scheduleMangerService.jobInitLoadFromDb();
			} else {
				_log.info("Job Not Start.");
			}

		}
	}
}
