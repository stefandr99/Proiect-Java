package client;

import entity.Chestionar;
import repo.ChestionarRepo;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    Joc joc;
    private Socket socket = null;
    Chestionar chestionar;
    static ChestionarRepo chestionarRepo;

    public ClientThread (Socket socket) {
        this.socket = socket ;
        chestionarRepo = new ChestionarRepo();
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true) {
                String request = in.readLine();
                String nume = request.split(" ")[1];
                if(request.startsWith("creare")) {
                    this.joc = new Joc(Server.games.size() + 1, nume);
                    chestionar = chestionarRepo.findById(1);
                    out.println(chestionar.toString());
                    out.flush();

                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println (e);
            }
        }
    }
}
