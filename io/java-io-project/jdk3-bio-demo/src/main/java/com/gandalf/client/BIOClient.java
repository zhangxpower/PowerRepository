package com.gandalf.client;

import lombok.extern.java.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

@Log
public class BIOClient {
    private static int threadNum = 1;

    public static void startClient(){
        final int threadNumTemp = threadNum++;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 7432));
            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Start Send Message.".getBytes());
            outputStream.flush();
            byte[] bytes = new byte[1024];
            while (true){
                if(inputStream.read(bytes) != -1){
                    log.info("[Client"+ threadNumTemp +" Receive]: " + new String(bytes));
                    outputStream.write(("Client"+ threadNumTemp +" Message.").getBytes());
                    outputStream.flush();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                BIOClient.startClient();
            }).start();
        }
    }
}
