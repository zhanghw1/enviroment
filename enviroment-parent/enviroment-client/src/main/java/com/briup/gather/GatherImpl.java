package com.briup.gather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.briup.bean.Enviroment;
import com.briup.util.FileBackUp;
import com.briup.util.FileNameEnums;

public class GatherImpl implements IGather {

	@SuppressWarnings("all")
	@Override
	public Collection<Enviroment> gather() {

		long count = 0;

		List<Enviroment> list = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(FileNameEnums.RAW_DATA.getPath()));

			Object object = FileBackUp.recover(FileNameEnums.CLIENT_COUNT_PATH.getPath(), true);
			if (object != null) {
				count += (long) object;
			}

			reader.skip(count);

			String line = null;

			Object oldData = FileBackUp.recover(FileNameEnums.OLD_DATA.getPath(), true);

			if (oldData != null) {
				list.addAll((Collection<? extends Enviroment>) oldData);
			}

			while ((line = reader.readLine()) != null) {

				count += line.length() + 2;

				String[] result = line.split("\\|");

				if (result == null || result.length == 0) {
					return null;
				}

				Enviroment enviroment = new Enviroment();
				enviroment.setSrcId(result[0]);
				enviroment.setDevId(result[1]);
				enviroment.setRegionId(Long.parseLong(result[2]));
				enviroment.setCount(Long.parseLong(result[4]));
				enviroment.setState(Integer.parseInt(result[5]));
				enviroment.setReviceState(Integer.parseInt(result[7]));
				enviroment.setGatherData(new Date(Long.parseLong(result[8])));
				if ("16".equals(result[3])) {
					enviroment.setName("温度");
					int wResult = Integer.parseInt(result[6].substring(0, 4), 16);
					double data = ((float) wResult * 0.00268127) - 46.85;
					enviroment.setData(data);
					list.add(enviroment);

					enviroment = new Enviroment();
					enviroment.setSrcId(result[0]);
					enviroment.setDevId(result[1]);
					enviroment.setRegionId(Long.parseLong(result[2]));
					enviroment.setName("湿度");
					enviroment.setCount(Long.parseLong(result[4]));
					enviroment.setState(Integer.parseInt(result[5]));
					int sResult = Integer.parseInt(result[6].substring(4, 8), 16);
					data = ((float) sResult * 0.00190735) - 6;
					enviroment.setData(data);
					enviroment.setReviceState(Integer.parseInt(result[7]));
					enviroment.setGatherData(new Date(Long.parseLong(result[8])));
					list.add(enviroment);
				} else if ("1280".equals(result[3])) {
					enviroment.setName("二氧化碳");
					double data = Integer.parseInt(result[6].substring(0, 4), 16);
					enviroment.setData(data);
					list.add(enviroment);
				} else {
					enviroment.setName("光照强度");
					double data = Integer.parseInt(result[6].substring(0, 4), 16);
					enviroment.setData(data);
					list.add(enviroment);
				}
			}

			reader.close();
			FileBackUp.store(FileNameEnums.CLIENT_COUNT_PATH.getPath(), count);
			return list;

		} catch (Exception e) {
			try {
				FileBackUp.store(FileNameEnums.OLD_DATA.getPath(), list);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
}
