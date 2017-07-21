package com.itmasir.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {

    private static final int PORT = 1234;

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
            Socket s = ss.accept();
            System.out.println("这是服务端，监听本机" + PORT + "端口");
            byte[] recData = null;
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            while (true) {
                recData = new byte[BUFFER_SIZE];
                int r = in.read(recData);
                // int r = in.read(recData);
                if (r > -1) {
                    String data = new String(recData);
                    if (data.trim().equals("over")) {
                        s.close();
                    }
                    System.out.println("读取到客户端发送的来数据：" + data);
                    out.write("这是服务端发给客户端的数据：".getBytes());
                    out.write(recData);
                } else {
                    System.out.println("数据读取完毕！");
                    s.close();
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("客户端已断线!");
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}