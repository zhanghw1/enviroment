package com.briup.receive;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ReceiveTest {

	private static final Logger logger = Logger.getLogger(ReceiveTest.class);

	@Test
	public void test1() {
		IReceive receive = new ReceiveImpl();
		receive.receive();
	}

	@Test
	public void test2() {
		logger.debug("日志信息1");
		logger.info("日志信息2");
		logger.warn("日志信息3");
		logger.error("日志信息4");
		logger.fatal("日志信息5");
	}

	@Test
	public void test3() {
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date.toString().substring(8));
	}
}
