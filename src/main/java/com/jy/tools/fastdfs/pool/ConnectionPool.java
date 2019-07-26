package com.jy.tools.fastdfs.pool;

import java.net.InetSocketAddress;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

import com.jy.tools.fastdfs.conn.Connection;

public class ConnectionPool extends GenericKeyedObjectPool<InetSocketAddress,Connection>{
	//默认构造函数
	public ConnectionPool(KeyedPooledObjectFactory<InetSocketAddress, Connection> factory,
			GenericKeyedObjectPoolConfig config) {
		super(factory, config);
	}
	//默认构造函数
	public ConnectionPool(KeyedPooledObjectFactory<InetSocketAddress, Connection> factory) {
		super(factory);
	}

}
