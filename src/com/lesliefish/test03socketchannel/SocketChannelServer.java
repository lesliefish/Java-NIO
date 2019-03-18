package com.lesliefish.test03socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SocketChannelServer {

    public static void startServer() {
        final String currentFilePath = System.getProperty("user.dir") + "/res/test.txt";

        try {
            SocketChannel server = SocketChannel.open();
            SocketAddress socketAddress = new InetSocketAddress(9999);
            server.connect(socketAddress);

            Path path = Paths.get(currentFilePath);
            FileChannel fileChannel = FileChannel.open(path);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
                server.write(buffer);
                buffer.clear();
            }

            fileChannel.close();
            System.out.println("File Sent.");
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
