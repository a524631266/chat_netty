package org.example.bytebuf;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;


public class ByteBufDemo {
    /**
     * 查看 bytebuf写数据之后的四个基本指标的变化
     */
    public static ByteBuf showRIWIRBWB() {
        // 最大容量 超过会报错。同时会扩容
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        showReadableWritable(buffer);
        for (int i = 0; i < 10; i++) {
            System.out.println("=========[" + i +"]");
            buffer.writeInt(10);
//        buffer.writeByte(10);
            showReadableWritable(buffer);
            System.out.println("=========");
        }
        return buffer;
    }

    private static void showReadableWritable(ByteBuf buffer) {
        System.out.println("readerIndex:" + buffer.readerIndex());
        System.out.println("readableBytes:" + buffer.readableBytes());
        System.out.println("writerIndex:" + buffer.writerIndex());
        System.out.println("writableBytes:" + buffer.writableBytes());
    }

    /**
     * slice释放空间
     */
    public static void testSlice(){
        ByteBuf byteBuf = showRIWIRBWB();
        doWith(byteBuf);
        byteBuf.release();
    }

    private static void doWith(ByteBuf byteBuf) {
        ByteBuf slice = byteBuf.slice();

        // 会报错的。
        slice.release();
    }

    private static void doWith2(ByteBuf byteBuf) {
        ByteBuf slice = byteBuf.slice();
        // 必须要谁
        slice.retainedSlice();
        // 会报错的。
        slice.release();
    }

    public static ByteBuf testBy() {
        // 最大容量 超过会报错。同时会扩容
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
//        System.out.println("allocate bytebuf(9, 100)" + buffer);
//        for (int i = 0; i < 10; i++) {
//            buffer.writeInt(i);
//        }
        buffer.writeBytes(new byte[]{1,2,3,4});
        System.out.println("allocate bytebuf(9, 100)" + buffer);
//
        System.out.println("getByte(3) return + " + buffer.getByte(0)); // 01
        System.out.println("getShort(3) return + " + buffer.getShort(1)); // 是以一个字节为位移的字节获取
        System.out.println("getInt(3) return + " + buffer.getInt(0));// 0x01020304;
        return buffer;
    }
    public static void main(String[] args) {
//        showRIWIRBWB();
//        testSlice();

        testBy();
    }

}
