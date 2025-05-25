package JavaNetworking.Exercise3;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

public class Worker extends Thread{
    Socket socket;
    String fileOutput;

    public Worker(Socket socket, String fileOutput) {
        this.socket = socket;
        this.fileOutput = fileOutput;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileOutput, true));

            out.write("HELLO " + socket.getInetAddress().toString() + "\n");
            out.flush();

            String line = in.readLine();

            if (!line.contains("HELLO")) {
                throw new IOException("Server returned an error");
            }

            out.write("SEND DAILY DATA" + "\n");
            out.flush();

            String line2 = in.readLine();

            if (line2.split(",").length != 3) {;
                throw new IOException("Server returned an error");
            }

            Calendar c = Calendar.getInstance();
            String d = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
            String data = d + ", " + line2;

            fileWriter.write(data + "\n");
            fileWriter.flush();

            out.write("OK" + "\n");
            out.flush();

            while(true) {
                line = in.readLine();
                if (line.equals("QUIT")) {
                    socket.close();
                    in.close();
                    out.close();
                    fileWriter.close();

                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
