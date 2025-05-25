package JavaNetworking.Exercise2;

import java.io.*;
import java.net.Socket;

public class Worker extends Thread {
    Socket socket;
    String logFile;

    public Worker(Socket socket, String logFile) {
        this.socket = socket;
        this.logFile = logFile;
    }

    public void run() {
        String mailTo = "";
        String mailFrom = "";
        String mailCC = "";

        BufferedReader in = null;
        BufferedWriter out = null;
        BufferedWriter log = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            log = new BufferedWriter(new FileWriter(logFile));

            // Server sends Message START
            out.write("START " + socket.getInetAddress().toString() + "\n");
            out.flush();

            log.write("START " + socket.getInetAddress().toString() + "\n");
            log.flush();

            // Server receives MAILTO
            String line = in.readLine();

            if (line == null || !line.contains("@")) {
                throw new IOException("Server returned a non valid email address");
            }

            mailTo = line.split(" ")[1];

            log.write(line + "\n");
            log.flush();

            // Server sends TNX
            out.write("TNX" + "\n");
            out.flush();

            log.write("TNX" + "\n");
            log.flush();

            // Server receives MAILFROM
            line = in.readLine();

            if (line == null || !line.contains("@")) {
                throw new IOException("Server returned a non valid email address");
            }

            mailFrom = line.split(" ")[1];

            log.write(line + "\n");
            log.flush();

            // Server sends 200
            out.write("200" + "\n");
            out.flush();

            log.write("200" + "\n");
            log.flush();

            // Server receives MAILCC

            line = in.readLine();

            if (line == null || !line.contains("@")) {
                throw new IOException("Server returned a non valid email address");
            }

            mailCC = line.split(" ")[1];

            log.write(line + "\n");
            log.flush();

            // Server sends RECEIVERS

            out.write("RECEIVERS: " + mailTo + " " + mailCC + "\n");
            out.flush();

            log.write("RECEIVERS: " + mailFrom + " " + mailCC + "\n");
            log.flush();

            // Receiving <DATA>
            String message;
            int counter = 0;
            while(true) {
                message = in.readLine();
                if (message.equals("?")) break;
                counter++;
            }

            // Server: received total lines
            out.write("RECEIVED: " + counter + " lines" + "\n");
            out.flush();

            log.write("RECEIVED: " + counter + " lines" + "\n");
            log.flush();

            String exit = "";
            while (true) {
                exit = in.readLine();
                if (exit.equals("EXIT")) {
                    socket.close();
                    in.close();
                    out.close();
                    log.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
