package JavaNetworking.Exercise5_UDP;

import java.io.IOException;
import java.net.*;

public class UDPClient extends Thread{
    int port;
    InetAddress address;

    DatagramSocket socket;
    String message;
    byte [] buffer;

    public UDPClient(int port, String address, String message) throws SocketException, UnknownHostException {
        this.port = port;
        this.address = InetAddress.getByName(address);
        this.socket = new DatagramSocket();
        this.message = message;
    }

    public void run() {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

        try {
            socket.send(packet);

            packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
            socket.receive(packet);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        UDPClient client = new UDPClient(9753, "127.0.0.1", "Hello!");
        client.start();
    }
}
