package com.lesliefish.test11asynfilechannel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureObjectTester {
    public static void test() {
        String filePath = "D:\\javaCopy.txt";
        printFileContents(filePath);

        Path path = Paths.get(filePath);
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(400);
            Future<Integer> result = channel.read(buffer, 0);
            while (!result.isDone()) {
                System.out.println("Task of reading file is in progress asynchronously.");
            }
            System.out.println("Reading done: " + result.isDone());
            System.out.println("Bytes read from file: " + result.get());

            buffer.flip();
            System.out.print("Buffer contents: ");
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            System.out.println(" ");
            buffer.clear();
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void printFileContents(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String textRead = bufferedReader.readLine();
            System.out.println("File contents: ");
            while (textRead != null) {
                System.out.println("     " + textRead);
                textRead = bufferedReader.readLine();
            }

            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
