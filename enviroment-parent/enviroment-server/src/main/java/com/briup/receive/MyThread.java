package com.briup.receive;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;

import com.briup.bean.Enviroment;
import com.briup.store.IStore;
import com.briup.store.StoreImpl;

// 自定义线程类
public class MyThread extends Thread {

	private Socket socket;

	public MyThread(Socket socket) {
		this.socket = socket;
	}

	@SuppressWarnings("all")
	@Override
	public void run() {
		try {
			// 反序列化
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

			Object object = ois.readObject();

			Collection<Enviroment> collection = (Collection<Enviroment>) object;

			// 存储数据
			IStore store = new StoreImpl();
			store.store(collection);

//			collection.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
