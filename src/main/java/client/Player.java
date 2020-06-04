package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player implements Runnable {
    String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;

        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter outSocket =  new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader (
                     new InputStreamReader(socket.getInputStream()))
        ) {

        } catch (IOException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
