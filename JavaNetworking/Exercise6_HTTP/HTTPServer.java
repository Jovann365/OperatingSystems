package JavaNetworking.Exercise6_HTTP;

import JavaNetworking.Exercise1.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer extends Thread {
    int port;

    public HTTPServer(int port) {
        this.port = port;
    }

    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                new HTTPWorker(socket).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HTTPServer server = new HTTPServer(8080);
        server.start();
    }
}
