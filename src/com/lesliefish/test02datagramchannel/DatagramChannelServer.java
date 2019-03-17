package com.lesliefish.test02datagramchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelServer {

    public static void startServer() throws IOException {
        DatagramChannel server = DatagramChannel.open();
        InetSocketAddress iAdd = new InetSocketAddress("localhost", 8989);
        //绑定ip 端口
        server.bind(iAdd);

        System.out.println("Server Started: " + iAdd);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 从客户端接收buffer
        SocketAddress remoteAdd = server.receive(buffer);
        // 改变buffer模式
        buffer.flip();
        int limits = buffer.limit();
        byte bytes[] = new byte[limits];
        buffer.get(bytes, 0, limits);
        String msg = new String(bytes);
        System.out.println("Client at " + remoteAdd + "  sent: " + msg);
        server.send(buffer,remoteAdd);
        server.close();
    }
}
