package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        int port = parsePort(args);
        try {
            try {
                clientSocket = new Socket("localhost", port);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String serverWord = in.readLine();
                System.out.println(serverWord);
                System.out.print("> ");
                String word = reader.readLine();
                while (!word.equals("signUp")) {
                    System.out.print("> ");
                    word = reader.readLine();
                }
                out.write(word + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println(serverWord);
                System.out.print("> ");
                word = reader.readLine();
                out.write(word + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println(serverWord);
                System.out.print("> ");
                word = reader.readLine();
                out.write(word + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println(serverWord);
            } finally {
                System.out.println("Client is closed!");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static int parsePort(String[] args) {
        String argName = "--port=";
        String message = "Expected 1 argument (--port=)";
        if (args.length != 1 || !args[0].startsWith(argName)) {
            System.err.println(message);
            System.exit(1);
        }
        return Integer.parseInt(args[0].substring(argName.length()));
    }
}
