package org.geetbang.sun_homework.No2.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过Socket编程实现http服务器
 */
public class HttpServerBySocket {

    private static final List<HttpServerBySocket> LIST = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        LIST.add(null);
        ServerSocket serverSocket = new ServerSocket(9090);
        while (true) {
            Socket socket = serverSocket.accept();
            serviceSocket(socket);
        }

    }

    private static void serviceSocket(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "路由转发1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
