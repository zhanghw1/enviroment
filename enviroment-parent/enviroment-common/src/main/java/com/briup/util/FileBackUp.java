package com.briup.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.StringUtils;

//备份文件数据
public interface FileBackUp {

	static void store(String path, Object obj) throws Exception {
		if (StringUtils.isBlank(path)) {
			return;
		}
		File file = new File(path);

		if (!file.exists()) {
			file.createNewFile();
		}

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	static Object recover(String path, boolean isDelete) throws Exception {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		File file = new File(path);

		if (!file.exists()) {
			return null;
		}

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object object = ois.readObject();
		ois.close();

		if (isDelete) {
			file.delete();
		}

		return object;
	}
}
