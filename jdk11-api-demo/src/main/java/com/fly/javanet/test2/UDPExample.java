package com.fly.javanet.test2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class UDPExample {
    public static void main(String[] args) {
        try(DatagramChannel channel = DatagramChannel.open()
                .bind(new InetSocketAddress(9999))
                .setOption(StandardSocketOptions.SO_REUSEADDR, true)) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                SocketAddress clientAddr = channel.receive(buffer);
                buffer.flip();
                String message = StandardCharsets.UTF_8.decode(buffer).toString();
                System.out.println("收到来自 " + clientAddr + " 的消息: " + message);
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
