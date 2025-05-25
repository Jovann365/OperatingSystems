package JavaNetworking.Exercise3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    int port;
    String fileOutput;

    public Server(int port, String fileOutput) {
        this.port = port;
        this.fileOutput = fileOutput;
    }

    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput, true));
            writer.write("date, No. new covid cases, No. hospitalized patients, No. recovered patients" + "\n");
            writer.flush();

            ServerSocket serverSocket = new ServerSocket(port);
            Socket newSocket;

            while (true) {
                newSocket = serverSocket.accept();
                new Worker(newSocket, fileOutput).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8888, "output.csv");
        server.start();
    }
}
