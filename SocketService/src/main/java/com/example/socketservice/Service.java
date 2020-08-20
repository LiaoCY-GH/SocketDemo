package com.example.socketservice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {
    public static void main(String[] args) throws IOException {

        try (ServerSocket serivce = new ServerSocket(30000)) {
            while (true) {
                //等待客户端连接
                Socket socket = serivce.accept();
                new Thread(new Runable(socket)).start();
            }
        }
    }
}