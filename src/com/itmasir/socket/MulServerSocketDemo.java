package com.itmasir.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MulServerSocketDemo {
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        int counter = 1;
        ServerSocket ss = new ServerSocket(PORT);
        while (true) {
            Socket s = ss.accept();
            System.out.println("第 " + (counter++) + " 个连接");
            Thread t = new Thread(new ThreadServerSocket(s));
            t.start();
        }
    }
}
