package com.itmasir.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketDemo {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 1234;

    public static void main(String[] args) throws UnknownHostException,
            IOException {
        Socket client = new Socket(HOST, PORT);
        OutputStream out = client.getOutputStream();
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println("发送心跳数据包");
                out.write("发送心跳包!".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.close();
            client.close();
        }
    }

}