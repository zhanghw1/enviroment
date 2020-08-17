package com.briup.gather;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.briup.send.ISend;
import com.briup.send.SendImpl;

public class ClientTest {

	@Test
	public void test1() {
		ISend send = new SendImpl();
		IGather gather = new GatherImpl();
		send.send(gather.gather());
	}

	@Test
	public void test2() {

	}

	public static void main(String[] args) {

//		Timer timer = new Timer();
//
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				System.out.println("任务一 start");
//				System.out.println("任务一 end");
//			}
//		}, 1000, 1000);
//
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				System.out.println("任务二 start");
//				System.out.println("任务二 end");
//			}
//		}, 2000, 2000);

//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				System.out.println("任务一");
//			}
//		}, 1000, 3000);

//		timer.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				System.out.println("任务二");
//			}
//		}, new Date(), 5000);

		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

//		executorService.schedule(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println("任务一 start");
//			}
//		}, 5, TimeUnit.SECONDS);

		executorService.scheduleAtFixedRate(() -> {
			System.out.println("任务一 start");
			System.out.println("任务一 aaa");
			System.out.println("任务一 bbb");
//			int a = 10 / 0;
			System.out.println("任务一 ccc");
			System.out.println("任务一 end");
		}, 1, 2, TimeUnit.SECONDS);

		executorService.scheduleAtFixedRate(() -> {
			System.out.println("任务二 start");
			System.out.println("任务二 aaa");
			System.out.println("任务二 bbb");
			System.out.println("任务二 ccc");
			System.out.println("任务二 end");
		}, 1, 2, TimeUnit.SECONDS);
	}
}
