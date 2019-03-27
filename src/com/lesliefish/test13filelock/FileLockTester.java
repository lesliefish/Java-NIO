package com.lesliefish.test13filelock;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLockTester {
    public static void test() {
        String input = "Demo text to be written in locked mode.";
        System.out.println("Input string to the test file is: " + input);
        ByteBuffer byteBuffer = ByteBuffer.wrap(input.getBytes());
        String filePath = "D:\\javacopy.txt";
        Path path = Paths.get(filePath);
        try {
            FileChannel channel = FileChannel.open(path, StandardOpenOption.APPEND);
            channel.position(channel.size() - 1);
            FileLock fileLock = channel.lock();
            System.out.println("The Lock is shared: " + fileLock.isShared());
            channel.write(byteBuffer);
            channel.close(); // Releases the Lock
            System.out.println("Content Writing is complete. Therefore close the channel and release the lock.");
            PrintFileCreated.print(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}