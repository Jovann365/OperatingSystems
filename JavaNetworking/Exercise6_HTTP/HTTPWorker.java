package JavaNetworking.Exercise6_HTTP;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HTTPWorker extends Thread {
    Socket socket;

    public HTTPWorker(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String request = in.readLine();
            Map<String, String> result = parseUrl(request);
            String name = result.getOrDefault("name", "default");
            String surname = result.getOrDefault("surname", "default");

            out.write("HTTP/1.1 200 OK" + "\n");
            out.write("Content-Type: text/html" + "\n");
            out.write("<html><head><title>Hello</title></head><body>" + "\n");
            out.write("<h1>Hello, " + name + " " + surname + "!</h1>" + "\n");
            out.write("</body></html>" + "\n");

            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Map<String, String> parseUrl(String url) {
        Map<String, String> map = new HashMap<>();
        if (!url.contains("?")) return map;

        String[] parts = url.split("\\s+");
        String query = parts[1].split("\\?")[1];
        String[] params = query.split("&");

        for (String param : params) {
            String[] keyValue = param.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

}
