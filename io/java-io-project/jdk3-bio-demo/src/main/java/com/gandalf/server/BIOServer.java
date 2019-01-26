package com.gandalf.server;




import lombok.Getter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Log
@Getter
public class BIOServer {
    private String name;
    private final static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void startServer(){

            try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(7432));
            while(true){
                final Socket acceptSocket = serverSocket.accept();
                handlerSocket(acceptSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handlerSocket(Socket acceptSocket) {
        executorService.execute(() -> {
            try {
                final InputStream inputStream = acceptSocket.getInputStream();
                final OutputStream outputStream = acceptSocket.getOutputStream();
                byte[] bytes = new byte[1024];
                while (true){
                    if(inputStream.read(bytes) != -1){
                        log.info("[Server Receive]: " + new String(bytes));
                        outputStream.write("Server Message.".getBytes());
                        outputStream.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        BIOServer.startServer();
    }
}
