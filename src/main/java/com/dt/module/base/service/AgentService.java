package com.dt.module.base.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: jinjie
 * @date: 2018年4月16日 下午4:56:00
 * @Description: TODO
 */
@Component
@EnableScheduling 
public class AgentService {
	@Scheduled(cron="0/5 * *  * * ? ")
	public void test() {
		System.out.println("111");
	}
}