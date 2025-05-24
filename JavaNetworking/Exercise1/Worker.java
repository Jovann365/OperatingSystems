package JavaNetworking.Exercise1;

import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Worker extends Thread{
    Socket socket;
    String logPath;
    static List<String> words = new ArrayList<>();

    public Worker(String logPath, Socket socket) {
        this.logPath = logPath;
        this.socket = socket;
    }

    public void run() {


        BufferedReader in = null;
        BufferedWriter out = null;
        BufferedWriter log = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            log = new BufferedWriter(new FileWriter(logPath));

            while (true) {
                String line = in.readLine();
                if (!line.equals("HANDSHAKE")) {
//                    System.out.println("Please enter HANDSHAKE to log in!");
                }
                else {
                    out.write("Logged in " + socket.getInetAddress().toString() + "\n");
                    out.flush();

//                    System.out.println("Logged in " + socket.getInetAddress().toString() + "\n");
                    break;
                }
            }

            while (true) {
                String line = in.readLine();

                if (line.equals("STOP")) {
                    out.write(words.size() + "\n");
                    out.flush();

                    out.write("LOGGED OUT\n");
                    out.flush();

                    break;
                }

                if (!words.contains(line)) {
                    words.add(line);

                    out.write(line + " NEMA" +"\n");
                    out.flush();

//                    System.out.println(line + "NEMA");

                    log.write(line + " " + new Date().toString() + " " + socket.getInetAddress().toString() + "\n");
                    log.flush();
                }
                else {
                    out.write(line + " IMA" +"\n");
                    out.flush();

//                    System.out.println(line + "IMA");
                }
            }

            System.out.println("CLOSING");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
