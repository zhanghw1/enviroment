package com.briup.receive;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.briup.bean.Enviroment;

public class ReceiveImpl implements IReceive {

	private static final Logger logger = Logger.getLogger(ReceiveImpl.class);

	// 多次不同数据用多线程处理
	@SuppressWarnings("all")
	@Override
	public void receive() {

		int port = 7911;
		ServerSocket server = null;
		Socket socket = null;

		List<Enviroment> list = new ArrayList<>();

		try {
			// 创建对象
			server = new ServerSocket(port);

			logger.info("服务器启动,监听端口号" + port);
			logger.info("准备接收" + server.getInetAddress() + "发送过来的数据...");
			while (true) {
				// 阻塞
				socket = server.accept();
				// 线程处理
				logger.info("正在接收" + server.getInetAddress() + "发送过来:数据");
				new MyThread(socket).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (server != null)
					server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
