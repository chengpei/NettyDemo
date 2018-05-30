package com.example.dataacqdemo.netty;

import com.example.dataacqdemo.event.MyEvent;
import com.example.dataacqdemo.services.BusinessService;
import com.example.dataacqdemo.utils.ApplicationContextUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyDemoServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(NettyDemoServerHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        logger.info("客户端[{}]连接", ctx.channel().id());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("客户端[{}]断开", ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        BusinessService businessService = ApplicationContextUtils.getBean("businessService", BusinessService.class);
        businessService.testService((String) msg);
        ApplicationContextUtils.publishEvent(new MyEvent(this, (String) msg));
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.info(ctx.channel().id() + " : " + cause.getMessage());
    }
}
