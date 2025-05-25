package JavaNetworking.Exercise3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    int port;
    String address;

    public Client(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getByName(address), port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner sc = new Scanner(System.in);

            out.write("HELLO " + socket.getLocalPort() + "\n");
            out.flush();
            System.out.println("HELLO " + socket.getLocalPort() + "\n");

            new Thread(() -> {
                while (true) {
                    try {
                        String line = sc.nextLine();
                        out.write(line + "\n");
                        out.flush();
                        if (line.equals("QUIT")) {
                            break;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

            new Thread(() -> {
                while(true) {
                    try {
                        String line = in.readLine();
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
        Client client = new Client(8888, "127.0.0.1");
        client.start();
    }
}
