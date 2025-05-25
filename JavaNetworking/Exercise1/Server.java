package JavaNetworking.Exercise1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class Server extends Thread {
    int port;
    String logPath;

    public Server(int port, String logPath) {
        this.port = port;
        this.logPath = logPath;
    }

    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {
                Socket socket = serverSocket.accept();
                new Worker(logPath, socket).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(7391, "log.txt");
        server.start();
    }
}
