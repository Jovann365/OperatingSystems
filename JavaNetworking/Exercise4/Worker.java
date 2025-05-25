package JavaNetworking.Exercise4;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

public class Worker extends Thread{
    Socket socket;
    String logFile;
    static int sum = 0;
    ReentrantLock lock = new ReentrantLock();

    public Worker(Socket socket, String numFile) {
        this.socket = socket;
        this.logFile = numFile;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedWriter fileOut = new BufferedWriter(new FileWriter("JavaNetworking/Exercise4/log.txt", true));
            BufferedReader fileIn = new BufferedReader(new FileReader(logFile));

            while (true) {
                String line = in.readLine();
                if (line.equals("HANDSHAKE")) {
                    out.write("Logged in: " + socket.getInetAddress().toString() + "\n");
                    out.flush();
                    break;
                }
            }

            String number;
            int localSum = 0;
            while (true) {
                number = fileIn.readLine();

                if (number.equals("STOP")) {
                    break;
                }

                int numberInt = Integer.parseInt(number);
                localSum += numberInt;
                fileOut.write(numberInt +" "+ Calendar.getInstance().get(Calendar.HOUR_OF_DAY) +" "+ socket.getInetAddress().toString() +"\n");
                fileOut.flush();
            }

            lock.lock();
            sum += localSum;
            out.write(sum + "\n");
            out.flush();
            lock.unlock();

            out.write("Logged out" + "\n");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
