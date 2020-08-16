package com.dt.module.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author algernonking
 * @version 创建时间：2017年8月1日 下午5:23:44 类说明
 */
public class WebContextListener implements ServletContextListener {
    private static Logger _log = LoggerFactory.getLogger(WebContextListener.class);

    public void contextInitialized(ServletContextEvent arg0) {
        _log.info("WebContextListener contextInitialized");
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        _log.info("WebContextListener contextDestroyed");
    }
}
