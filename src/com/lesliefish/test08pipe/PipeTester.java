package com.lesliefish.test08pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTester {
    public static void test() {
        try {
            Pipe pipe = Pipe.open();
            Pipe.SinkChannel sinkChannel = pipe.sink();
            String testData = "check java nio channels pipe.";
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            byteBuffer.clear();
            byteBuffer.put(testData.getBytes());
            byteBuffer.flip();
            // 写入sink通道
            while (byteBuffer.hasRemaining()) {
                sinkChannel.write(byteBuffer);
            }

            Pipe.SourceChannel sourceChannel = pipe.source();
            byteBuffer = ByteBuffer.allocate(512);
            while (sourceChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    char ch = (char) byteBuffer.get();
                    System.out.print(ch);
                }
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
