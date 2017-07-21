package com.itmasir.socket;

import java.io.IOException;

public class MulClientSocketDemo {
    public static void main(String[] args) throws IOException {
        int counter = 4;
        while ((counter--) > 0) {
            new Thread(new ThreadClientSocket()).start();
        }
    }
}
