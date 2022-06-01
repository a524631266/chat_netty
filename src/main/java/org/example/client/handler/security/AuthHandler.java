package org.example.client.handler.security;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j2;
import org.example.config.ChatConfiguration;

@Log4j2
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(msg);
        // 如果
        if(!ChatConfiguration.hasLogin(ctx.channel())){
            ctx.channel().close();
            return;
        }
        // 热插拔核心代码，当验证之后，可以直接删除掉数据
        ctx.pipeline().remove(this);
        super.channelRead(ctx, msg);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(ChatConfiguration.hasLogin(ctx.channel())){
            log.info("删除当前的handler ，表示已经验证过了，后续无需再认证");
        } else  {
            log.info("无登陆验证，删除 handler");
        }
        super.handlerRemoved(ctx);
    }
}
