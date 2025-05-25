package JavaNetworking.Exercise4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    int port;
    String numFile;

    public Server(int port, String numFile) {
        this.port = port;
        this.numFile = numFile;
    }

    public void run() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            Socket socket;

            while (true) {
                socket = serverSocket.accept();
                new Worker(socket, numFile).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(7391, "JavaNetworking/Exercise4/numbers.txt");
        server.start();
    }
}
