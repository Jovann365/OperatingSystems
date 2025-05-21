package LaboratoryExercises.Lab4_Networking;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private String address;
    private int port;
    private String index;

    public Client(String address, int port, String index) {
        this.address = address;
        this.port = port;
        this.index = index;
    }

    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getByName(address), port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("chatlog231044.txt", true));
            Scanner sc = new Scanner(System.in);

            writer.write("hello:" + index + "\n");
            writer.flush();

            fileWriter.write("hello:" + index + "\n");
            fileWriter.flush();

            String response = reader.readLine();
            System.out.println(response);

            fileWriter.write(response + "\n");
            fileWriter.flush();

            if (!response.contains("Succesfully")) {
                throw new IOException("Не сте конектирани на серверот!");
            }

            writer.write("hello:" + index + "\n");
            writer.flush();

            fileWriter.write("hello:" + index + "\n");
            fileWriter.flush();

            response = reader.readLine();
            System.out.println(response);

            fileWriter.write(response + "\n");
            fileWriter.flush();

            if (!response.contains("welcome")) {
                throw new IOException("Не сте конектирани на серверот!");
            }

            new Thread(() -> {

                while(true) {
                String line = sc.nextLine();
                    try {
                        writer.write(line + "\n");
                        writer.flush();
                        fileWriter.write(line + "\n");
                        fileWriter.flush();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
            }}).start();

            new Thread(() -> {
                String line;
                while(true) {
                    try {
                        line = reader.readLine();
                        System.out.println(line);
                        fileWriter.write(line + "\n");
                        fileWriter.flush();
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
        Client client = new Client("194.149.135.49", 9753, "231044");
        client.start();
    }
}
