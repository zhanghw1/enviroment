package com.briup.gather;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.briup.send.SendImpl;

public class Client2Test {

	public static void main(String[] args) {
		// 创建定时器
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		// 创建任务
		SendImpl send = new SendImpl();

		GatherImpl gather = new GatherImpl();

		scheduledExecutorService.scheduleAtFixedRate(() -> {
			send.send(gather.gather());
		}, 1, 600, TimeUnit.SECONDS);
	}
}
