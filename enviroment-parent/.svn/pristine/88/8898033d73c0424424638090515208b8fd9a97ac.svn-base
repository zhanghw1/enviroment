package com.briup.send;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.briup.bean.Enviroment;
import com.briup.util.FileBackUp;
import com.briup.util.FileNameEnums;

public class SendImpl implements ISend {

	private static final Logger logger = Logger.getLogger(SendImpl.class);

	@SuppressWarnings("all")
	@Override
	public void send(Collection<Enviroment> collection) {

		Socket socket = null;
		String ip = "127.0.0.1";
		int port = 7911;

		try {

			Object object = FileBackUp.recover(FileNameEnums.CLIENT_DATA.getPath(), true);

			if (object != null) {
				collection.addAll((Collection<? extends Enviroment>) object);
			}

			socket = new Socket(ip, port);
			logger.info("客户端已成功连接到服务器端" + ip + ":" + port);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

			oos.writeObject(collection);
			oos.flush();
			oos.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				FileBackUp.store(FileNameEnums.CLIENT_DATA.getPath(), collection);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
