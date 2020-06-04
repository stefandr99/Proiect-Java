package client;

import entity.Chestionar;
import repo.ChestionarRepo;

import java.util.ArrayList;
import java.util.List;

public class Joc {
    int id;
    volatile int playersCount;
    volatile List<String> players;
    volatile boolean win = false;
    Chestionar chestionar;

    public Joc(int id, String player) {
        this.id = id;
        this.playersCount = 1;

        players = new ArrayList<>();
        players.add(player);

        chestionar = new Chestionar();
    }

    public Chestionar getRunda() {
        return ClientThread.chestionarRepo.findById(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Chestionar getChestionar() {
        return chestionar;
    }

    public void setChestionar(Chestionar chestionar) {
        this.chestionar = chestionar;
    }

    public void incrementPlayers() {
        this.playersCount++;
    }

    public void addPlayer(String name) {
        players.add(name);
    }


}
