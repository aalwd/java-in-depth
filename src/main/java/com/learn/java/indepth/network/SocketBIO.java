package com.learn.java.indepth.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketBIO {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9090);
        System.out.println("step 1 : new ServerSocket : 9090");
        while(true) {
//            System.in.read();
            Socket client = server.accept(); // 等待接受客户端请求, 阻塞
            System.out.println("step2 : client\t " + client.getPort());

            new Thread(() -> {
                InputStream in = null;
                try {
                    in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while(true) {
//                        System.in.read();
                        String dataLine = reader.readLine();
                        if(dataLine != null) {
                            System.out.println(dataLine);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();

        }
    }
}
