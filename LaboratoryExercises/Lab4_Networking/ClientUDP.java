package LaboratoryExercises.Lab4_Networking;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClientUDP extends Thread {
    private String serverName;
    private int serverPort;

    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buffer;

    public ClientUDP( String serverName, int serverPort) throws UnknownHostException, SocketException {
        this.serverName = serverName;
        this.serverPort = serverPort;

        this.socket = new DatagramSocket();
        this.address = InetAddress.getByName("194.149.135.49");
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        if (message.length() > 6) {
            System.out.println("Message is too long");
            return;
        }

        buffer = message.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, serverPort);

        try {
            socket.send(packet);

            byte [] newBuffer=new byte[256];
            DatagramPacket receivedPacket = new DatagramPacket(newBuffer, newBuffer.length, address, serverPort);

            socket.receive(receivedPacket);

            System.out.println(new String(receivedPacket.getData(), 0, receivedPacket.getLength()));
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        ClientUDP client = new ClientUDP("194.149.135.49", 9753);
        client.start();
    }
}
