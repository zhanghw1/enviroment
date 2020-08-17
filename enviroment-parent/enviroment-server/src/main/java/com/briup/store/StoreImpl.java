package com.briup.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.briup.bean.Enviroment;
import com.briup.util.ConnectionUtils;
import com.briup.util.FileBackUp;
import com.briup.util.FileNameEnums;

public class StoreImpl implements IStore {

	private static final Logger logger = Logger.getLogger(StoreImpl.class);

	@SuppressWarnings("all")
	@Override
	public void store(Collection<Enviroment> collection) throws Exception {
		Object object = FileBackUp.recover(FileNameEnums.SERVER_DATA.getPath(), true);
		if (object != null) {
			collection.addAll((Collection<? extends Enviroment>) object);
		}

		Connection connection = ConnectionUtils.getConnection(false);
		try {

			int num = 0;
			PreparedStatement ps = null;
			String day = "0";

			for (Enviroment enviroment : collection) {
				// 获取日期天数
				String day1 = enviroment.getGatherData().toString().substring(8);

				if (!day.equals(day1)) {
					if (ps != null) {
						ps.executeBatch();
						ps.clearBatch();
						ps.close();
					}
					day = day1;
					String sql = "insert into tbl_data_" + day + " values(common_seq.nextval,?,?,?,?,?,?,?,?,?)";
					ps = connection.prepareStatement(sql);
				}

				ps.setString(1, enviroment.getSrcId());
				ps.setString(2, enviroment.getDevId());
				ps.setLong(3, enviroment.getRegionId());
				ps.setString(4, enviroment.getName());
				ps.setLong(5, enviroment.getCount());
				ps.setInt(6, enviroment.getState());
				ps.setDouble(7, enviroment.getData());
				ps.setInt(8, enviroment.getReviceState());
				ps.setDate(9, enviroment.getGatherData());
				ps.addBatch();
				num++;
				if (num % 100 == 0) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}

			if (ps != null) {
				ps.executeBatch();
				ps.clearBatch();
			}

			logger.info("存储数据成功");
			connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			FileBackUp.store(FileNameEnums.SERVER_DATA.getPath(), collection);
		}
	}

}
