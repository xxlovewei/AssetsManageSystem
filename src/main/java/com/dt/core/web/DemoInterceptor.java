package com.dt.core.web;
//
//
///** 
//* @author 作者 Lank 
//* @version 创建时间：2017年8月2日 下午9:38:15 
//* 类说明 
//*/
// 
//
//import com.zheng.common.util.PropertiesFileUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 拦截器
// * Created by shuzheng on 2017/3/24.
// */
//public class DemoInterceptor extends HandlerInterceptorAdapter {
//
//    private static Logger _log = LoggerFactory.getLogger(DemoInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 过滤ajax
//        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
//            return true;
//        }
//       // String appName = PropertiesFileUtil.getInstance().get("app.name");
//        //request.setAttribute("");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//    }
//
//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        super.afterConcurrentHandlingStarted(request, response, handler);
//    }

//}
