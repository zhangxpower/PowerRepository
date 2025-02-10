package com.fly.javanet.test2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPSender {
    public static void main(String[] args) throws Exception {
        DatagramChannel channel = DatagramChannel.open();

        String message = "Hello UDP via DatagramChannel!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());

        InetSocketAddress target = new InetSocketAddress("127.0.0.1", 9999);
        channel.send(buffer, target);  // 发送数据

        System.out.println("UDP 数据已发送: " + message);
        channel.close();
    }
}