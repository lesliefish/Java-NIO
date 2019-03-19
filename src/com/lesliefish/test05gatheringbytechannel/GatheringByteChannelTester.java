package com.lesliefish.test05gatheringbytechannel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;

public class GatheringByteChannelTester {
    private static String currentFilePath = System.getProperty("user.dir") + "/res/new_test.txt";

    public static void test() {

        String stream1 = "Gather data stream first";
        String stream2 = "Gather data stream second";
        ByteBuffer bLen1 = ByteBuffer.allocate(1024);
        ByteBuffer bLen2 = ByteBuffer.allocate(1024);
        // 用两个buffer存储上面两个字符串
        ByteBuffer bstream1 = ByteBuffer.wrap(stream1.getBytes());
        ByteBuffer bstream2 = ByteBuffer.wrap(stream2.getBytes());
        int len1 = stream1.length();
        int len2 = stream2.length();
        // 按字符串长度写入文件
        bLen1.asIntBuffer().put(len1);
        bLen2.asIntBuffer().put(len2);
        System.out.println("Gathering : Len1 = " + len1);
        System.out.println("Gathering : Len2 = " + len2);
        //  把数据写入文件
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(currentFilePath);
            GatheringByteChannel gather = fileOutputStream.getChannel();
            gather.write(new ByteBuffer[]{bLen1, bLen2, bstream1, bstream2});
            fileOutputStream.close();
            gather.close();
        } catch (FileNotFoundException exObj) {
            exObj.printStackTrace();
        } catch (IOException ioObj) {
            ioObj.printStackTrace();
        }
    }
}
