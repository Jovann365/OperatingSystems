package JavaNetworking.Exercise4;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    int port;
    String address;

    public Client(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void run() {
        try {
            Socket socket = new Socket(address, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader fileIn = new BufferedReader(new FileReader("JavaNetworking/Exercise4/numbers.txt"));

            out.write("HANDSHAKE" + "\n");
            out.flush();

            String line = in.readLine();
            System.out.println(line);

            String number;
            while ((number = fileIn.readLine()) != null) {
                out.write(number + "\n");
                out.flush();
            }

            line = in.readLine();
            System.out.println(line);

            line = in.readLine();
            System.out.println(line);

            socket.close();
            in.close();
            out.close();
            fileIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(7391, "127.0.0.1");
        client.start();
    }
}
