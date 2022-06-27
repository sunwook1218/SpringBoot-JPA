package com.example.demo.socket.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class NonSslSocket{

    private String host;
    private int port;

    public NonSslSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run(int messageLength) {
        try {
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress(host, port);
            socket.connect(address);

            ClientSocket clientSocket = new ClientSocket(socket);
            clientSocket.sendFixedLength(messageLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
