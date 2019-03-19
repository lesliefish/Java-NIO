package com.lesliefish.test04scatteringbytechannel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;

public class ScatteringByteChannelTester {
    private static String currentFilePath = System.getProperty("user.dir") + "/res/test.txt";

    public static void test() {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(32); // 文件38个字节  小于38则会使用到byteBuffer2
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(6);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(currentFilePath);

            ScatteringByteChannel scatteringByteChannel = fileInputStream.getChannel();
            scatteringByteChannel.read(new ByteBuffer[]{byteBuffer1, byteBuffer2});
            byteBuffer1.position(0);
            byteBuffer2.position(0);
            int len1 = byteBuffer1.limit();
            int len2 = byteBuffer2.limit();
            // buffer limit
            System.out.println("Scattering : byteBuffer1 = " + len1);
            System.out.println("Scattering : byteBuffer2 = " + len2);

            // buffer存储信息
            System.out.println("Scattering : byteBuffer1 info: " + byteBuffer1);
            System.out.println("Scattering : byteBuffer2 info: " + byteBuffer2);

            // buffer内容
            System.out.println("Scattering : byteBuffer1 is " + new String(byteBuffer1.array()));
            System.out.println("Scattering : byteBuffer2 is " + new String(byteBuffer2.array()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}

/*
Scattering : byteBuffer1 = 32
Scattering : byteBuffer2 = 6
Scattering : byteBuffer1 info: java.nio.HeapByteBuffer[pos=0 lim=32 cap=32]
Scattering : byteBuffer2 info: java.nio.HeapByteBuffer[pos=0 lim=6 cap=6]
Scattering : byteBuffer1 is counter strike.
my name is lesli
Scattering : byteBuffer2 is efish.
*/
