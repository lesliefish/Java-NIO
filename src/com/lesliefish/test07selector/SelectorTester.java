package com.lesliefish.test07selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTester {

    public static void test() {
        try {
            String demoText = "this is a demo string.";
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer byteBuffer = ByteBuffer.allocate(256);

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    int interestOps = key.interestOps();
                    System.out.println("interestOps is " + interestOps);
                    if (key.isAcceptable()) {
                        // 新客户端连接
                        SocketChannel client = serverSocketChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        // 可读事件
                        SocketChannel client = (SocketChannel) key.channel();
                        client.read(byteBuffer);
                        if (new String(byteBuffer.array()).trim().equals(demoText)) {
                            client.close();
                            System.out.println("Not accepting client messages anymore");
                        }
                        byteBuffer.flip();
                        client.write(byteBuffer);
                        byteBuffer.clear();
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
