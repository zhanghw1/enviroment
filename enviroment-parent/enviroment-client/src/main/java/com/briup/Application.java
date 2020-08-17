package com.briup;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.briup.gather.GatherImpl;
import com.briup.gather.IGather;
import com.briup.send.ISend;
import com.briup.send.SendImpl;

public class Application {

	public static void main(String[] args) {

		// 创建定时器线程池
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);

		// 创建采集对象
		IGather gather = new GatherImpl();

		// 创建发送数据对象
		ISend send = new SendImpl();

		// 创建任务并执行
		threadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				send.send(gather.gather());
			}
		}, 1, 600, TimeUnit.SECONDS);
	}
}
