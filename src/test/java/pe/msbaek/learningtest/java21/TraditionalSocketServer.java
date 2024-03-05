package pe.msbaek.learningtest.java21;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * echo "hello" | nc localhost 9090
 */
public class TraditionalSocketServer {
    public static void main(String[] args) throws IOException {
        var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try(var serverSocket = new ServerSocket(9090)) {
            Socket clientSocket = serverSocket.accept();
            executor.submit(() -> {
                try {
                    handleRequest(clientSocket);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void handleRequest(Socket clientSocket) throws IOException, InterruptedException {
        var next = -1;
        try(var baos = new ByteArrayOutputStream()) {
            try (var in = clientSocket.getInputStream()) {
                while((next = in.read()) != -1) {
                    baos.write(next);
                }
            }
            var data = baos.toString();
            System.out.printf("request: %s\n", data);
        }
    }
}
