package com.example.socketservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Runable implements Runnable {

    Socket socket = null;

    public Runable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String line = null;
        InputStream input;
        OutputStream output;
        String str = "hello world!";
        try {
            //向客户端发送信息
            output = socket.getOutputStream();
            input = socket.getInputStream();
            BufferedReader bff = new BufferedReader(
                    new InputStreamReader(input));
            output.write(str.getBytes("gbk"));
            output.flush();
            //半关闭socket
            socket.shutdownOutput();
            //获取客户端的信息
            while ((line = bff.readLine()) != null) {
                System.out.print(line);
            }
            //关闭输入输出流
            output.close();
            bff.close();
            input.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
