package com.dt.core.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dt.core.common.shiro.SimpleFilterChainDefinitionsService;
import com.dt.core.common.util.SpringContextUtil;
import com.dt.module.schedule.service.ScheduleMangerService;

/**
 * spring容器初始化完成事件 Spring框架加载完成后会publishContextRefreshedEvent事件 创建ContextRefreshedEvent事件监听类
 */
@Component("myServletContextListener")
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Logger _log = LoggerFactory.getLogger(ApplicationContextListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// root application context
		if (null == event.getApplicationContext().getParent()) {
			SpringContextUtil.getApplicationContext();
			System.out.println(">>>>> spring初始化完毕 <<<<<");
			_log.info("更新Chain");
			SimpleFilterChainDefinitionsService.me().updatePermission();
			_log.info("启动job");
			//ScheduleMangerService scheduleMangerService = ScheduleMangerService.me();
			//scheduleMangerService.scheduleStart();
			//scheduleMangerService.jobInitLoadFromDb();
			// SimpleFilterChainDefinitionsService.me().updatePermission();
			// spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
			// Map<String, Object> baseServices =
			// contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
			// for(Object service : baseServices.values()) {
			// _log.debug(">>>>> {}.initMapper()",
			// service.getClass().getName());
			// try {
			// Method initMapper = service.getClass().getMethod("initMapper");
			// initMapper.invoke(service);
			// } catch (Exception e) {
			// _log.error("初始化BaseService的initMapper方法异常", e);
			// e.printStackTrace();
			// }
			// }
			// 系统入口初始化
			// Map<String, BaseInterface> baseInterfaceBeans =
			// contextRefreshedEvent.getApplicationContext().getBeansOfType(BaseInterface.class);
			// for(Object service : baseInterfaceBeans.values()) {
			// _log.debug(">>>>> {}.init()", service.getClass().getName());
			// try {
			// Method init = service.getClass().getMethod("init");
			// init.invoke(service);
			// } catch (Exception e) {
			// _log.error("初始化BaseInterface的init方法异常", e);
			// e.printStackTrace();
			// }
			// }
		}
	}
}
