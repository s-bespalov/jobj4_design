package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class EchoServer {

    private static class Params {
        private final Map<String, String> dict;

        private static Map<String, String> parse(String raw) {
            var result = new HashMap<String, String>();
            new Scanner(raw).useDelimiter("&")
                    .tokens()
                    .forEach(p -> {
                        var eq = p.indexOf("=");
                        var key = p.substring(0, eq);
                        var val = eq == p.length() - 1 ? "" : p.substring(eq + 1);
                        result.put(key, val);
                    });
            return result;
        }

        private static void check(String params) {
            new Scanner(params).useDelimiter("&")
                    .tokens()
                    .forEach(p -> {
                        var eq = p.indexOf("=");
                        if (eq == -1) {
                            throw new IllegalArgumentException(String.format("Incorrect parameter '%s'", p));
                        }
                        var key = p.substring(0, eq);
                        if (key.isEmpty()) {
                            throw new IllegalArgumentException(String.format("Incorrect parameter '%s', empty key", p));
                        }
                    });
        }

        public Params(String raw) {
            check(raw);
            dict = parse(raw);
        }

        public String get(String key) {
            if (!dict.containsKey(key)) {
                throw new NoSuchElementException(String.format("There is no parameter '%s'", key));
            }
            return dict.get(key);
        }

        public boolean has(String key) {
            return dict.containsKey(key);
        }
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    var string = input.readLine();
                    if (string != null && !string.isEmpty()) {
                        System.out.println(string);
                        if (string.contains("/?")) {
                            var rawParams = new Scanner(string)
                                    .tokens()
                                    .filter(l -> l.startsWith("/?"))
                                    .map(l -> l.substring(2))
                                    .findFirst()
                                    .orElse("");
                            if (!rawParams.isEmpty()) {
                                var params = new Params(rawParams);
                                if (params.has("msg") && Objects.equals(params.get("msg"), "Bye")) {
                                    server.close();
                                    break;
                                }
                            }
                        }
                        string = input.readLine();
                    }
                    while (string != null && !string.isEmpty()) {
                        System.out.println(string);
                        string = input.readLine();
                    }
                    output.flush();
                }
            }
        }
    }
}