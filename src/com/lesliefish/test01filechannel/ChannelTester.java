package com.lesliefish.test01filechannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTester {
    public static void test() {
        String currentFilePath = System.getProperty("user.dir") + "/res/test.txt";
        System.out.println(currentFilePath);
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(currentFilePath, "r");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip(); // 调用flip之后，读写指针指到缓存头部
                while (byteBuffer.hasRemaining()) {
                    System.out.print((char) byteBuffer.get() + " ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
