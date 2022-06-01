package org.example.stable;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.TimeUnit;

/**
 * 空闲检测handler，如果没有响应就直接关闭链接
 */
@Log4j2
public class IMIdleStateHandler extends IdleStateHandler {
    public IMIdleStateHandler(){
        // 15秒没有读到，就表示无效链接
        // 0,0表示表示无限
        super(15, 0, 0 , TimeUnit.SECONDS);
    }

    /**
     * 没有读到，会自动每隔15秒会自动触发。
     * @param ctx channel上下文
     * @param evt 心跳事件
     * @throws Exception 返回一场
     */
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.info(evt);
        super.channelIdle(ctx, evt);
    }

    /**
     * 当服务器关闭，也会删除相关的线程
     * @param ctx 上下文
     * @throws Exception 返回异常
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("remove");
        super.handlerRemoved(ctx);
    }
}
