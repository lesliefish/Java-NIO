package com.lesliefish.test02datagramchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelClient {

    public static void sendMsgToServer() throws IOException {
        DatagramChannel client = null;
        client = DatagramChannel.open();
        client.bind(null);

        String msg = "hello, java nio.";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

        InetSocketAddress serverAddr = new InetSocketAddress("localhost", 8989);
        client.send(buffer, serverAddr);
        client.receive(buffer);
        buffer.flip();

        client.close();
    }

}
