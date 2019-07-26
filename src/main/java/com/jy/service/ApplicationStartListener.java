package com.jy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${fileupload.FastDFS.maxTotal}")
	private int maxTotal;
	@Value("${fileupload.FastDFS.maxTotalPerKey}")
	private int maxTotalPerKey;
	@Value("${fileupload.FastDFS.maxIdlePerKey}")
	private int maxIdlePerKey;

	@Value("${fileupload.FastDFS.soTimeout}")
	private int soTimeout;
	@Value("${fileupload.FastDFS.connectTimeout}")
	private int connectTimeout;
	@Value("${fileupload.FastDFS.trackers}")
	private String trackers;

	private static Logger logger = LoggerFactory.getLogger(ApplicationStartListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		// 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
		logger.info("我的父容器为：" + contextRefreshedEvent.getApplicationContext().getParent());
		logger.info("初始化时我被调用了。");

		// 初始化核心socket连接
//		client.init();

		//fastdfs client init
//		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(soTimeout, connectTimeout);
//		GenericKeyedObjectPoolConfig conf = new GenericKeyedObjectPoolConfig();
//		conf.setMaxTotal(maxTotal);
//		conf.setMaxTotalPerKey(maxTotalPerKey);
//		conf.setMaxIdlePerKey(maxIdlePerKey);
//		ConnectionPool connectionPool = new ConnectionPool(pooledConnectionFactory, conf);
//		Set<String> trackerSet = new HashSet<String>();
//		for (String tracker : trackers.split(",")) {
//			trackerSet.add(tracker);
//		}
//		DefaultCommandExecutor commandExecutor = new DefaultCommandExecutor(trackerSet, connectionPool);
//		trackerClient = new DefaultTrackerClient(commandExecutor);
//		storageClient = new DefaultStorageClient(commandExecutor, trackerClient);
//		fastClient.setTrackerClient(trackerClient);
//		fastClient.setStorageClient(storageClient); 
	} 
}