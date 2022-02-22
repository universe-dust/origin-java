package com.originlang.server;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 停机命令
 */
public class ShutdownCommand {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8005, 1,
                    InetAddress.getByName("localhost"));
            while (true) {
                Socket socket = null;
                StringBuilder command = new StringBuilder();
                InputStream stream = null;
                socket = serverSocket.accept();
                socket.setSoTimeout(10 * 1000);
                stream = socket.getInputStream();
                byte[] commands = new byte[8];
                stream.read(commands);
                for (byte b : commands)
                    command.append((char) b);
                System.out.println(command.toString());
                if (command.toString().equals("SHUTDOWN"))
                    break;
            }
        } catch (IOException e) {
        }
    }
}

