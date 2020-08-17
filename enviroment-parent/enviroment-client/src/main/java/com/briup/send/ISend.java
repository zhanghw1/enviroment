package com.briup.send;

import java.util.Collection;

import com.briup.bean.Enviroment;

//发送数据
public interface ISend {

	void send(Collection<Enviroment> collection);
}
