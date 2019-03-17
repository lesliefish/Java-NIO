package com.lesliefish.test02datagramchannel;

import java.io.IOException;

public class DatagramChannelTester {

    public static void test() {
        try {
            DatagramChannelServer.startServer();
            //DatagramChannelClient.sendMsgToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
