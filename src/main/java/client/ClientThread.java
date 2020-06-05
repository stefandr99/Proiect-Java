package client;

import entity.Chestionar;
import repo.ChestionarRepo;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {
    Joc joc;
    int rand;
    private Socket socket = null;
    Chestionar chestionar;

    public ClientThread (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true) {
                String request = in.readLine();

                if (request.startsWith("creare")) {
                    String nume = request.split(" ")[1];
                    String maxi = request.split(" ")[2];
                    int maxiJucatori = Integer.parseInt(maxi);

                    this.joc = new Joc(Server.jocuri.size() + 1, nume, maxiJucatori);
                    Server.jocuri.add(this.joc);
                    System.out.println("Am creat un joc de catre " + nume + " cu jucatori: " + maxiJucatori + " lungime: " + Server.getJocuri().isEmpty());
                    out.println(1);
                    out.flush();

                    this.joc.incrementPlayers();
                    this.rand = joc.getPlayersCount();
                    while (joc.getPlayersCount() < maxiJucatori) ;
                    joaca(in, out);
                }
                else if(request.startsWith("show")){
                    String raspuns = "";
                    if(Server.jocuri.isEmpty())
                        raspuns = "Nu exista niciun joc. Creeaza tu unul!";
                    else {
                        raspuns = "<html>";
                        for (Joc j : Server.jocuri) {
                            int i = j.id;
                            List<String> jucatoriActivi = new ArrayList<>(j.players);
                            String total = "";
                            for (String s : jucatoriActivi) {
                                total += s + ", ";
                            }
                            total = total.substring(0, total.length() - 2);
                            raspuns += "Jocul " + i + " cu jucatorii: " + total + "<br>";
                        }
                        raspuns += "</html>";
                    }
                    out.println(raspuns);
                    out.flush();
                }
                else if (request.startsWith("alatura")) {
                    String nume = request.split(" ")[1];
                    String id = request.split(" ")[2];
                    int idJoc = Integer.parseInt(id);

                    joc = Server.getById(idJoc);
                    joc.addPlayer(nume);
                    joc.incrementPlayers();
                    this.rand = joc.getPlayersCount();
                    out.println(joc.getPlayersCount());
                    out.flush();
                    joaca(in, out);
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

    public void joaca(BufferedReader in, PrintWriter out) throws IOException {
        while(true) {
            synchronized (joc.getChestionarRepo()) {
                while (joc.getRand() != rand) {
                    try {
                        joc.getChestionarRepo().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                chestionar = joc.getChestionarRepo().findById(1);
                out.println(chestionar.toString());
                out.flush();

                String raspuns = in.readLine();
                //Server.x = rand;

                joc.setRand(rand % joc.getPlayersCount() + 1);
                joc.getChestionarRepo().notify();
            }
        }
    }
}
