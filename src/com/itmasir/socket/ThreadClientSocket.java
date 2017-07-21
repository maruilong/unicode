package com.itmasir.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

class ThreadClientSocket implements Runnable {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 1234;

    private Socket client;

    public ThreadClientSocket() {
        client = new Socket();
        try {
            client.connect(new InetSocketAddress(HOST, PORT), 500);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            OutputStream out = client.getOutputStream();
            int counter = 0;
            while (true) {
                System.out.println("Thread-->"
                        + Thread.currentThread().getName());
                out.write("Heart Beat !".getBytes());
                System.out.println(client + "================" + (counter++));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
