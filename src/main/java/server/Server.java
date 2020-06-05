package server;

import client.ClientThread;
import client.Joc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static volatile int x = 0;
    public static final int PORT = 8100;
    public volatile static List<Joc> jocuri = new ArrayList<>();

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

    public static List<Joc> getJocuri() {
        return jocuri;
    }

    public static void setJocuri(List<Joc> games) {
        Server.jocuri = games;
    }

    public static Joc getById(int id) {
        for(Joc j : jocuri) {
            if(j.getId() == id)
                return j;
        }
        return null;
    }

    public static void addJoc(Joc joc){
        jocuri.add(joc);
    }

    public static void main (String [] args ) throws IOException {
        Server server = new Server();
    }
}
