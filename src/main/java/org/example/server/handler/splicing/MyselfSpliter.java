package org.example.server.handler.splicing;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.example.codec.PacketCodeC;

/**
 * 屏蔽非本协议的客户端
 */
public class MyselfSpliter extends LengthFieldBasedFrameDecoder {

    public static final int LENGTH_FIELD_OFFSET = 7;
    public static final int LENGTH_FIELD_LENGTH = 4;

    public MyselfSpliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    /**
     * 屏蔽逻辑。
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 需要屏蔽的地方在 in 这里，因为 上层channelRead经过长度字段过滤之后的数据才会放到 decode方法中执行
        // 可以使用开头4个字节是否是魔数来屏蔽非本协议的客户端。
        // 注意 in中的read偏移量不是0！！！！！
        if(in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER){
            System.out.println("1123213");
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
