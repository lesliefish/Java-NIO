package com.lesliefish.test03socketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class SocketChannelClient {
    public static void test() {
        final String currentFilePath = System.getProperty("user.dir") + "/res/client_test.txt";

        try {
            ServerSocketChannel serverSocket = null;
            SocketChannel client = null;
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(9000));
            client = serverSocket.accept();

            System.out.println("Connection Set:  " + client.getRemoteAddress());
            Path path = Paths.get(currentFilePath);
            FileChannel fileChannel = FileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (client.read(buffer) > 0) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }
            fileChannel.close();
            System.out.println("File Received.");
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
