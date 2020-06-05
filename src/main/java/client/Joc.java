package client;

import entity.Chestionar;
import repo.ChestionarRepo;

import java.util.ArrayList;
import java.util.List;

public class Joc {

    int id;
    int rand;
    volatile int playersCount;
    int maxPlayers;
    volatile List<String> players;
    volatile boolean win = false;
    ChestionarRepo chestionarRepo;
    Chestionar chestionar;

    public Joc(int id, String player, int maxPlayers) {
        this.id = id;
        this.maxPlayers = maxPlayers;
        this.playersCount = 0;
        this.rand = 1;

        players = new ArrayList<>();
        players.add(player);

        chestionar = new Chestionar();
        chestionarRepo = new ChestionarRepo();
    }

    public Chestionar getRunda() {
        return chestionarRepo.findById(1);
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

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public ChestionarRepo getChestionarRepo() {
        return chestionarRepo;
    }

    public void setChestionarRepo(ChestionarRepo chestionarRepo) {
        this.chestionarRepo = chestionarRepo;
    }
}
