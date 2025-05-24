package JavaNetworking.Exercise1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    String address;
    int port;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getByName(address), port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner sc = new Scanner(System.in);

            new Thread(() -> {
                String line;
                while(true) {
                    line = sc.nextLine();
                    try {
                        out.write(line + "\n");
                        out.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

            new Thread(() -> {
                String line;
                while(true) {
                    try {
                        line = in.readLine();
                        if (line == null) {
                            break;
                        }
                        System.out.println(line);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }).start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 7391);
        client.start();
    }
}
