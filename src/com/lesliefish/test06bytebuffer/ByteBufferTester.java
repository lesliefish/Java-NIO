package com.lesliefish.test06bytebuffer;

import java.nio.CharBuffer;

public class ByteBufferTester {
    public static void test() {
        // 10字节空间申请
        CharBuffer charBuffer = CharBuffer.allocate(10);
        String text = "bufferdemo";
        System.out.println("输入的文本: " + text);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // 把字符放入charBuffer.
            charBuffer.put(c);
        }
        int buffPos = charBuffer.position();
        System.out.println("把字符放入charBuffer后，charBuffer的位置: " + buffPos);
        charBuffer.flip();
        System.out.println("读取charBuffer文本内容:");
        while (charBuffer.hasRemaining()) {
            System.out.println(charBuffer.get());
        }
        //位置设置为5.
        charBuffer.position(5);
        System.out.println("当前位置 : " + charBuffer.position());
        //标记一下这个位置
        charBuffer.mark();
        //把位置改为6
        charBuffer.position(8);
        System.out.println("当前位置 : " + charBuffer.position());
        //调用reset方法重置回原来的位置.
        //如果未标记会抛出InvalidMarkException异常
        charBuffer.reset();
        System.out.println("当前位置 : " + charBuffer.position());
    }
}
