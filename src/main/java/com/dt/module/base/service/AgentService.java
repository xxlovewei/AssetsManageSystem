package com.dt.module.base.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author: jinjie
 * @date: 2018年4月16日 下午4:56:00
 * @Description: TODO
 */
@Service
public class AgentService {
	@Scheduled(fixedRate = 1000 * 2)
	public void test() {
		System.out.println("111");
	}
}
