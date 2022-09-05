package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public Server(UsersService usersService) {
    }

    public static void start(int port) {
        String username;
        String password;

        try {
            try {
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
                UsersService usersService = context.getBean("usersService", UsersService.class);
                server = new ServerSocket(port);
                System.out.println("Server is up!");
                try (Socket clientSocket = server.accept()) {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    out.write("Hello from Server!" + "\n");
                    out.flush();
                    String word = in.readLine();
                    out.write("Enter username:" + "\n");
                    out.flush();
                    username = in.readLine();
                    out.write("Enter password:" + "\n");
                    out.flush();
                    password = in.readLine();
                    out.write("Successful!" + "\n");
                    out.flush();
                    usersService.signUp(username, password);
                } finally {
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Server is closed!");
                server.close();
            }
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }
    }
}
