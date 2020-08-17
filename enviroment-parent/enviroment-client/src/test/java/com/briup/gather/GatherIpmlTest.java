package com.briup.gather;

import java.util.Collection;

import org.junit.Test;

import com.briup.bean.Enviroment;

public class GatherIpmlTest {

	@Test
	public void test1() {
		IGather gather = new GatherImpl();
		Collection<Enviroment> collection = gather.gather();
		collection.forEach(System.out::println);
		System.out.println(collection.size());
	}

	@Test
	public void test2() {
		int data = Integer.parseInt("522", 16);
		System.out.println(data);
	}
}
