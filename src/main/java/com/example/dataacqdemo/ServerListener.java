package com.example.dataacqdemo;


import com.example.dataacqdemo.netty.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

@Repository
public class ServerListener implements ApplicationListener<ContextRefreshedEvent> {
	
	Logger logger = LoggerFactory.getLogger(ServerListener.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//使用该注解 防止两个容器时执行 2次 
		if(event.getApplicationContext().getParent() == null){
			int port = 37775;
			logger.info("Spring加载完成.......启动Netty服务，监听端口 {}",port);

			new Thread(new NettyServer(port)).start();
		}
		
	}

}
