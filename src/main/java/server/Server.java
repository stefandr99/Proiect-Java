package server;

import client.ClientThread;
import client.Joc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int PORT = 8100;
    public volatile static List<Joc> games = new ArrayList<>();

    public Server() throws IOException {
        ServerSocket serverSocket = null ;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println ("Waiting for players ...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        } finally {
            assert serverSocket != null;
            serverSocket.close();
        }
    }

    public static void main ( String [] args ) throws IOException {
        Server server = new Server();
    }
}
