```Java
@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
    //使用该注解 防止两个容器时执行 2次 
    if(event.getApplicationContext().getParent() == null){
        int port = 37775;
        logger.info("Spring加载完成.......启动Netty服务，监听端口 {}",port);
        new Thread(new NettyServer(port)).start();
    }
}
```
