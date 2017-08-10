package com.dt.core.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** 
* @author 作者 Lank 
* @version 创建时间：2017年8月1日 下午5:23:44 
* 类说明 
*/
 
public class WebContextListener  implements ServletContextListener {


	public void contextInitialized(ServletContextEvent arg0)
	{
		//System.out.println("start");
		
		
		 
		
	}
	
	public void contextDestroyed(ServletContextEvent arg0)
	{
		//System.out.println("stop");
		//ScheduleMangerService.me().scheduleStop();
	}
}

