package com.itmasir.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ThreadServerSocket implements Runnable {
    private Socket server;

    public ThreadServerSocket(Socket socket) {
        server = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in = server.getInputStream();
            OutputStream out = server.getOutputStream();
            Scanner sc = new Scanner(in);
            PrintWriter pw = new PrintWriter(out, true/* 自动刷新 */);
            pw.println("Enter BYE quit ");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                pw.println("echo: " + line);
                if (line.trim().equals("BYE")) {
                    break;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}