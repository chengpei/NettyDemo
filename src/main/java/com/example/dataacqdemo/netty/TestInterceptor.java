package com.example.dataacqdemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestInterceptor extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(TestInterceptor.class);

    private ParamsPool paramsPool = new ParamsPool();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if(msg instanceof String){
            String str = (String) msg;
            if(paramsPool.add(str)){
                ctx.fireChannelRead(str);
            }else {
                logger.error("忽略数据 == {}", str);
            }
        }else if(msg instanceof ByteBuf){
            ByteBuf btf = (ByteBuf) msg;
            int startIndex = btf.readerIndex();
            int i = btf.readableBytes();
            byte[] bt = new byte[i];
            btf.readBytes(bt);
            if(bt.length > 0){
                if(paramsPool.add(ByteBufUtil.hexDump(bt))){
                    btf.readerIndex(startIndex);
                    ctx.fireChannelRead(btf);
                }else {
                    logger.error("忽略数据 == {}", ByteBufUtil.hexDump(bt));
                }
            }
        }
    }
}
