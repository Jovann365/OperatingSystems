package JavaNetworking.Exercise2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    int port;
    String logFile;

    public Server(int port, String logFile) {
        this.port = port;
        this.logFile = logFile;
    }

    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                new Worker(socket, logFile).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8765, "server.txt");
        server.start();
    }
}
