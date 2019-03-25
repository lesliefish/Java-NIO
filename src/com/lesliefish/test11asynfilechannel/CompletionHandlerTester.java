package com.lesliefish.test11asynfilechannel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CompletionHandlerTester {
    public static void test() {
        String input = "Content to be written to the file.";
        System.out.println("Input string: " + input);
        byte[] byteArray = input.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        Path path = Paths.get("D:\\fileCopy.txt");
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
            CompletionHandler completionHandler = new CompletionHandler() {
                @Override
                public void completed(Object result, Object attachment) {
                    System.out.println(attachment + " completed and " + result + " bytes are written.");
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println(attachment + " failed with exception:");
                    exc.printStackTrace();
                }
            };

            channel.write(buffer, 0, "Async Task", completionHandler);
            channel.close();
            printFileContents(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printFileContents(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String textRead = bufferedReader.readLine();
            System.out.println("File contents:  ");
            while (textRead != null) {
                System.out.println("    " + textRead);
                textRead = bufferedReader.readLine();
            }
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
