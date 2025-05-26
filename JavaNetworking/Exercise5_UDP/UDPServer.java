package JavaNetworking.Exercise5_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.SQLOutput;
import java.util.ServiceConfigurationError;

public class UDPServer extends Thread {
    DatagramSocket socket;
    byte [] buffer;

    public UDPServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        this.buffer = new byte[256];
    }

    public void run() {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            try {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("RECEIVED: " + received);

                packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                socket.send(packet);

                String response = new String(packet.getData(), 0, packet.getLength());
                System.out.println(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        UDPServer server = new UDPServer(9753);
        server.start();
    }
}
